package com.demoJava.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demoJava.demo.entity.PendidikanEntity;

@Repository
public interface PendidikanRepository extends JpaRepository<PendidikanEntity, Integer>{

	@Query(value = "select * from pendidikan_entity where id = ?", nativeQuery = true)
	List<PendidikanEntity> getPendidikanById(Integer id);
	
	@Query(value = "select * from pendidikan_entity where person_id = ?", nativeQuery = true)
	List<PendidikanEntity> getPendidikanByPersonId(Integer personId);
	
}
