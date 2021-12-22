package com.example.file_vault.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.file_vault.model.User;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(User user, MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
