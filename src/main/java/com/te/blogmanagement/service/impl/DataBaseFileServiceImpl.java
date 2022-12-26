package com.te.blogmanagement.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.te.blogmanagement.entity.DatabaseFile;
import com.te.blogmanagement.exception.FileStorageException;
import com.te.blogmanagement.repository.DataBaseFileUpload;
import com.te.blogmanagement.service.DataBaseFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DataBaseFileServiceImpl implements DataBaseFileService {
	private final DataBaseFileUpload dataBaseFileUpload;

	@Override
	public DatabaseFile storageFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DatabaseFile dbFile = new DatabaseFile(null, fileName, file.getContentType(), file.getBytes());

			return dataBaseFileUpload.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file ");
		}
	}

	public DatabaseFile getFile(String fileId) throws FileNotFoundException {
		return dataBaseFileUpload.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
	}

}
