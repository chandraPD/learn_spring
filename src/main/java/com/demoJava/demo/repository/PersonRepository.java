package com.demoJava.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demoJava.demo.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{
	
//	@Query(value = "select * from person_entity where first_name = ?", nativeQuery = true)
	List<PersonEntity> findByFirstName(String firstName);
	List<PersonEntity> findByLastName(String lastName);
	Optional<PersonEntity> findById(Integer id);
	
	@Query(value = "select first_name from person_entity where id = ?", nativeQuery = true)
	String findFirstNameById(Integer id);
	
}
