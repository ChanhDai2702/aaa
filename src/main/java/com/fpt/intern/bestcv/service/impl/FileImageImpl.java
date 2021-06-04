package com.fpt.intern.bestcv.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.intern.bestcv.service.FileImageService;

@Service
public class FileImageImpl implements FileImageService {

	private final Path root = Paths.get("./src/main/resources/static/images");

	@Override
	public String saveImage(MultipartFile file, String filename) {
				try {	
					
					if (!Files.exists(root)) {
			            Files.createDirectories(root);
			        }
					String type = file.getOriginalFilename().split("\\.")[1];
					
					String fileName= filename+"."+type;
					Files.copy(file.getInputStream(), this.root.resolve(fileName));
					return fileName;
				} catch (Exception e) {
					throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
				}
	

//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		if(fileName.isEmpty() == true || fileName.contains("..")){
//
//			System.out.println("not a a valid file");
//
//		}else {
//
//			try {
//				return  Base64.getEncoder().encodeToString(file.getBytes());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//		return null;	
	}
	@Override
	public Resource load(String filename) {
		try {
			if (!Files.exists(root)) {
	            Files.createDirectories(root);
	        }
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
}
