package com.te.blogmanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.te.blogmanagement.entity.DatabaseFile;

public interface DataBaseFileService {

	DatabaseFile storageFile(MultipartFile file);

}
