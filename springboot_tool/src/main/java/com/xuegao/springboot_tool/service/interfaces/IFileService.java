package com.xuegao.springboot_tool.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <br/> @PackageName：com.xuegao.springboot_tool.service.interfaces
 * <br/> @ClassName：IFileService
 * <br/> @Description：
 * <br/> @author：80004960
 * <br/> @date：2020/8/27 15:55
 */
public interface IFileService {

    Void uploadFileService(MultipartFile... fileArr) throws IOException;

    void transformFile() throws IOException, InterruptedException;
}