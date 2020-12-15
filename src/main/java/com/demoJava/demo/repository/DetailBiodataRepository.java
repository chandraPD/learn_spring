package com.demoJava.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoJava.demo.entity.DetailBiodataEntity;

@Repository
public interface DetailBiodataRepository extends JpaRepository<DetailBiodataEntity, Integer> {
	
	
}
