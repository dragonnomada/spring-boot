package com.example.delivery.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.delivery.model.Package;

public interface PackageRepository extends CrudRepository<Package, Long> {

}
