package com.shop.webshop.core.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IUploadFileService {
    String uploadFileNotInProject(String uploadDir ,String fileName ,MultipartFile file, HttpServletRequest request) throws Exception;

    String uploadFileInProject(String uploadDir ,String fileName ,MultipartFile file, HttpServletRequest request) throws Exception;
}
