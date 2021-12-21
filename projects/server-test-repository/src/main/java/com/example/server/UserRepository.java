package com.example.server;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findByName(String name);
	
	public List<User> findByAgeBetween(int min, int max);
	
	@Query("select user from User user where user.name like %:name% and user.age >= :age")
	public List<User> findByNameAndAge(@Param("name") String likeName, @Param("age") int minAge);
	
}
