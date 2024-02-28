package com.newpark.resources.utils;

import com.qcloud.cos.*;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @Author xxs18
 * @Description 腾讯云对象存储
 * @Date 2023/12/19 14:18
 **/
@Getter
@Setter
public class CosUtils {

    private  String tmpSecretId = "AKIDZ1HJ6B9vRl0aznQuDHGhy1ILD7mIdldI";

    private String tmpSecretKey = "c4LOFsIUv98sMdhaD5nIenkTyPdfVAcr";

    private String sessionToken = "";

    private String addr = "ap-shanghai";

    private String bucketName = "newpark-1257014531";

//    public static void main(String[] args) {
//        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
//
//
//
//        BasicSessionCredentials cred = new BasicSessionCredentials(tmpSecretId, tmpSecretKey, sessionToken);
//
//
//        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
//        COSClient cosClient = new COSClient(cred, clientConfig);
//
//        // 指定要上传的文件
//        File localFile = new File("E:\\图片\\1.jpg");
//        // 指定文件将要存放的存储桶
//        String bucketName = "newpark-1257014531";
//        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
//        String key = "folder/test.jpg";
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//
//    }
}
