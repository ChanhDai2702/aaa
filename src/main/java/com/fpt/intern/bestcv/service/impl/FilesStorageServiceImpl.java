package com.fpt.intern.bestcv.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.intern.bestcv.service.FilesStorageService;


@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	private String file = "./src/main/resources/static/images";
	private final Path root = Paths.get(file);

	@Override
	public String save(MultipartFile file) {
		try {
			if (!Files.exists(root)) {
	            Files.createDirectories(root);
	        }
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			return file.getOriginalFilename();
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public String saveWithCustomName(MultipartFile file, String customName) {
		try {
			if (!Files.exists(root)) {
	            Files.createDirectories(root);
	        }
			String type = file.getOriginalFilename().split("\\.")[1];
			String fileName= customName+"."+type;
			Files.copy(file.getInputStream(), this.root.resolve(fileName));
			return fileName;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
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

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}