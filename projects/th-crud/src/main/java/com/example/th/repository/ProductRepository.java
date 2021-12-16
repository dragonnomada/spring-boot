package com.example.th.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.example.th.model.Product;

//@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
