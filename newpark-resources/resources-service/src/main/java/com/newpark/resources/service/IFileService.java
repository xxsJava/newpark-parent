package com.newpark.resources.service;

import com.newpark.base.model.vo.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/29 9:35
 **/
public interface IFileService {

    List<String> uploadFile(List<MultipartFile> files);

    R<?> uploadVideos(List<MultipartFile> files,String uploadId);

}
