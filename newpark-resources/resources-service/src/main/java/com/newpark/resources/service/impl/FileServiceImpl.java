package com.newpark.resources.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.newpark.base.exception.BizException;


import com.newpark.base.model.vo.R;
import com.newpark.core.utils.StringUtils;
import com.newpark.resources.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret;

    @Override
    public List<String> uploadFile(List<MultipartFile> files) {

        if (StringUtils.isNull(files) || files.size() < 1) {
            throw new BizException("请上传文件");
        }

        List<String> fileUrlList = new ArrayList<>(files.size());
        this.handleUploads(files, fileUrlList, null);

        return fileUrlList;
    }

    @Override
    public R<?> uploadVideos(List<MultipartFile> files,String uploadId) {
        if (StringUtils.isNull(files) || files.size() < 1) {
            throw new BizException("请上传文件");
        }

        List<String> fileUrlList = new ArrayList<>(files.size());


        return R.ok(this.handleVideos(files, fileUrlList, uploadId));
    }


    private void handleUploads(List<MultipartFile> file, List<String> fileUrlList, String fileName) {

        String url = "";
        Integer i = 1;
        for (MultipartFile multipartFile : file) {
            // 1.获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 2.截取后缀名
            String imgSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 3.生成唯一名
            String newFileName = StringUtils.isNotEmpty(fileName) ? fileName + i + imgSuffix : UUID.randomUUID().toString() + imgSuffix;
            // 4.日期目录
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = dateFormat.format(new Date());
            // 5.合成路径
            String finalFileName = dataPath + "/" + newFileName;
            // 别忘了bucketName
            //url = "https://"+bucketName+"."+endPoint+"/"+fileName;
            String endPointStr = endPoint.substring(endPoint.lastIndexOf("oss"));
            url = "http://" + bucketName + "." + endPointStr + "/" + finalFileName;
            i++;
            try {
                OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentDisposition("inline");
                if (imgSuffix.contains("pdf")) {
                    metadata.setContentType("application/pdf");
                }
                metadata.setHeader("x-oss-object-acl", "public-read");
                // 文件上传
                InputStream inputStream = multipartFile.getInputStream();
                ossClient.putObject(bucketName, finalFileName, inputStream, metadata);
                ossClient.shutdown();
                fileUrlList.add(url);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("COMMON_FAIL");
            }
        }
    }

    /**
     * 视频上传
     * @param file
     * @param fileUrlList
     * @param uploadId
     */

    private String handleVideos(List<MultipartFile> file, List<String> fileUrlList, String uploadId) {

        String url = "";
        Integer i = 1;

        // 4.日期目录
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dataPath = dateFormat.format(new Date());

        String endPointStr = endPoint.substring(endPoint.lastIndexOf("oss"));
        url = "https://" + bucketName + "." + endPointStr + "/" + dataPath + "/" + "video/" + uploadId + "/m3u8/index.m3u8";
        for (MultipartFile multipartFile : file) {
            // 1.获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 2.截取后缀名
            String imgSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 5.合成路径
            String finalFileName = dataPath + "/" + "video/" + uploadId + "/ts/" + originalFilename;
            if(imgSuffix.contains("m3u8")){
                finalFileName = dataPath + "/" + "video/" + uploadId + "/m3u8/index.m3u8";
            }

            // 别忘了bucketName
            //url = "https://"+bucketName+"."+endPoint+"/"+fileName;
            i++;
            try {
                OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentDisposition("inline");
                if (imgSuffix.contains("pdf")) {
                    metadata.setContentType("application/pdf");
                }
                metadata.setHeader("x-oss-object-acl", "public-read");
                // 文件上传
                InputStream inputStream = multipartFile.getInputStream();
                ossClient.putObject(bucketName, finalFileName, inputStream, metadata);
                ossClient.shutdown();
                fileUrlList.add(url);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("COMMON_FAIL");
            }


        }

        return url;
    }

}
