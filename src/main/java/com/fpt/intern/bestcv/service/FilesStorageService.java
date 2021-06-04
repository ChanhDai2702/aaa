package com.fpt.intern.bestcv.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

	public String save(MultipartFile file);
	
	public String saveWithCustomName(MultipartFile file, String customName);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();
}
