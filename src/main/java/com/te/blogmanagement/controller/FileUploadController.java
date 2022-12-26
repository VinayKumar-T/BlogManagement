package com.te.blogmanagement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.te.blogmanagement.entity.DatabaseFile;
import com.te.blogmanagement.service.DataBaseFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/api")
@RestController
public class FileUploadController {

	private final DataBaseFileService dataBaseFileService;

	@PostMapping(path = "/uploadFile")
	public Response uploadFile(@RequestParam("file") MultipartFile file) {
		DatabaseFile databaseFile = dataBaseFileService.storageFile(file);

		String fileDownload = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(null).toUriString();
		return new Response();
	}

	@PostMapping("/uploadMultipleFiles")
	public Stream<Object> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file));

	}
}
