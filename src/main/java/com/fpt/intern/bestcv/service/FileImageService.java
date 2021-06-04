package com.fpt.intern.bestcv.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileImageService {
	String saveImage(MultipartFile file,String filename);

	Resource load(String filename);
}
