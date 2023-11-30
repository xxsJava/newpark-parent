package com.newpark.resources.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;

import com.newpark.base.constant.Constant;
import com.newpark.base.exception.BizException;
import com.newpark.base.model.vo.R;
import com.newpark.redis.utils.RedisKeys;
import com.newpark.redis.utils.RedisUtils;

import com.newpark.resources.entity.dto.PartETagDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jack
 * @date 2023/8/11
 */
@Slf4j
@Component
public class OssUtils {


    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret;

    private OSS ossClient;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String LOCK_KEY = "sharding_lock";

    @PostConstruct
    public void init() {
        ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
    }

    /**
     * 分块上传完成获取结果
     */
    public String completePartUploadFile(String fileKey, String uploadId, List<PartETagDTO> partETags) {
        Map<String, List<PartETagDTO>> listMap = partETags.stream().collect(Collectors.groupingBy(PartETagDTO::getETag));
        List<PartETag> partETags1 = Convert.toList(PartETag.class, partETags);
        List<PartETag> collect = partETags1.stream().map(x -> {
            if (listMap.containsKey(x.getETag())) {
                x.setPartCRC(Long.parseLong(listMap.get(x.getETag()).get(Constant.INTEGER_ZERO).getPartCRC()));
            }
            return x;
        }).collect(Collectors.toList());
        CompleteMultipartUploadRequest request = new CompleteMultipartUploadRequest(bucketName, fileKey, uploadId, collect);
        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(request);
        String downLoadUrl = getDownloadUrl(fileKey, bucketName);
        return downLoadUrl;
    }

    /**
     * @param fileKey  文件名称
     * @param is       文件流数据
     * @param uploadId oss唯一分片id
     * @param fileMd5  文件的md5值（非必传）
     * @param partNum  第几片
     * @param partSize 设置分片大小
     * @return
     */
    public PartETag partUploadFile(String fileKey, InputStream is, String uploadId, String fileMd5, int partNum, long partSize) {
        UploadPartRequest uploadPartRequest = new UploadPartRequest();
        uploadPartRequest.setBucketName(bucketName);
        uploadPartRequest.setUploadId(uploadId);
        uploadPartRequest.setPartNumber(partNum);
        uploadPartRequest.setPartSize(partSize);
        uploadPartRequest.setInputStream(is);
        uploadPartRequest.setKey(fileKey);
        uploadPartRequest.setMd5Digest(fileMd5);
        UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
        return uploadPartResult.getPartETag();
    }

    /**
     * 分块上传完成获取结果
     */
    public String getUploadId(String fileKey) {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, fileKey);
        // 初始化分片
        InitiateMultipartUploadResult unrest = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = unrest.getUploadId();
        return uploadId;
    }

    /**
     * 获取bucket文件的下载链接
     *
     * @param pathFile   首字母不带/的路径和文件
     * @param bucketName
     * @return 上报返回null, 成功返回地址
     */
    public String getDownloadUrl(String pathFile, String bucketName) {
        StringBuffer url = new StringBuffer();
        url.append(endPoint).append("/");
        if (pathFile != null && !"".equals(pathFile)) {
            url.append(pathFile);
        }
        return url.toString().replace("http://", "http://" + bucketName + ".");
    }

    /**
     * 下载文件
     *
     * @param objectName 文件名称
     * @return url
     */
    public String getUploadUrl(String objectName) {

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        // 设置URL过期时间为1小时
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成带签名的URL
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        log.info("Download URL: " + url.toString());
        // 关闭OSSClient
        ossClient.shutdown();
        return url.toString().substring(0, url.toString().lastIndexOf("?"));
    }


    public R<UploadDTO> getUploadId(UploadDTO dto) {

        UploadDTO result = new UploadDTO();
        String taskId = UUID.randomUUID().toString().replaceAll("-", "");
        String taskKey = taskId + dto.getFileName();
        result.setTaskId(taskId);
        result.setFileName(dto.getFileName());
        String uploadId = this.getUploadId(taskKey);
        result.setUploadId(uploadId);
        result.setPartNumber(Constant.INTEGER_ZERO);
        redisUtils.set(RedisKeys.ShardingConfig.key(uploadId), result);
        return R.ok(result);
    }

    /**
     * 处理分片上传逻辑  ShardingConfig
     *
     * @param dto 参数
     * @return R.ok
     */
    public R<String> handelShardingFile(UploadDTO dto) throws IOException, InterruptedException {

        UploadDTO oldDto = Convert.convert(UploadDTO.class, dto);
        // 获取分布式锁
        boolean lockAcquired = acquireLock();
        if (lockAcquired) {
            //取redis中的PartETags，在分片合成文件中需要以此为依据，合并文件返回最终地址
            UploadDTO redisParam = redisUtils.get(RedisKeys.ShardingConfig.key(dto.getUploadId()), UploadDTO.class);
            if (redisParam != null) {
                dto.setPartETags(redisParam.getPartETags());
            }
            dto.setFileName(redisParam.getFileName());
            dto.setTaskId(redisParam.getTaskId());
            dto.setMd5(redisParam.getMd5());
            dto.setPartNumber(dto.getPartNumber() + 1);
            String uploadId = dto.getUploadId();
            InputStream inputStream = dto.getFile().getInputStream();
            Map<Integer, PartETagDTO> partETags = dto.getPartETags();
            if (null == dto.getPartETags()) {
                partETags = new HashMap<>();
            }
            //分片上传
            try {
                PartETag partETag = this.partUploadFile(dto.getTaskId() + dto.getFileName(), inputStream, uploadId, dto.getMd5(), dto.getPartNumber(), dto.getPartSize());
//                log.info("原始数据:{}",JSON.toJSONString(partETag));
                PartETagDTO partETagDTO = Convert.convert(PartETagDTO.class, partETag);
                partETagDTO.setPartCRC(partETag.getPartCRC().toString());
                partETags.put(dto.getPartNumber(), partETagDTO);
                dto.setFile(null);
                dto.setPartETags(partETags);
                redisUtils.set(RedisKeys.ShardingConfig.key(dto.getUploadId()), dto);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("上传异常");
            } finally {
//                log.info("释放锁");
                releaseLock();
            }
        } else {
//            log.info("获取锁失败等待3秒后重新执行分片上传逻辑");
            Thread.sleep(3 * 1000);
            this.handelShardingFile(oldDto);
        }
        return R.ok();
    }

    public R<List<String>> handleMergeFile(String uploadId) {

        try {
            Thread.sleep(3 * 1000);
            UploadDTO redisParam = redisUtils.get(RedisKeys.ShardingConfig.key(uploadId), UploadDTO.class);
            log.info("获取到的分片参数:{}", JSON.toJSONString(redisParam));
            if (redisParam != null) {
                //合并文件，注意：partETags必须是所有分片的所以必须存入redis，然后取出放入集合
                String url = this.completePartUploadFile(redisParam.getTaskId() + redisParam.getFileName(), uploadId, new ArrayList<>(redisParam.getPartETags().values()));
                log.info("碎片合成文件地址:{}", url);
                redisUtils.delete(RedisKeys.ShardingConfig.key(uploadId));
                return R.ok(Arrays.asList(url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.failed("分片文件合并异常");
    }

    private boolean acquireLock() {
        Boolean lockAcquired = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY, "locked");
        if (lockAcquired != null && lockAcquired) {
            stringRedisTemplate.expire(LOCK_KEY, Duration.ofDays(5000L));
            return true;
        }
        return false;
    }

    private void releaseLock() {
        stringRedisTemplate.delete(LOCK_KEY);
    }

}
