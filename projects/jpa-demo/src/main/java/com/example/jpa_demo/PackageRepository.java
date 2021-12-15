package com.example.jpa_demo;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PackageRepository extends CrudRepository<Package, Long> {

	//@Query("select into ...")
	//public List<Package> findByGuid(String guid);
	
}
