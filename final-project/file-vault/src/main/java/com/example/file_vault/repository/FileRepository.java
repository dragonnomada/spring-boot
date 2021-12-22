package com.example.file_vault.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.file_vault.model.File;

public interface FileRepository extends CrudRepository<File, Long> {

}
