package com.demoJava.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoJava.demo.dto.PendidikanDto;
import com.demoJava.demo.entity.PendidikanEntity;
import com.demoJava.demo.entity.PersonEntity;
import com.demoJava.demo.repository.PendidikanRepository;
import com.demoJava.demo.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/pendidikan")
public class PendidikanController {

	@Autowired
	PendidikanRepository pendidikanRepository;
	
	@Autowired
	PersonRepository personRepository;
	
//	get all data
	@GetMapping("/get-all")
	public List<PendidikanEntity> getPendidikan(){
		List<PendidikanEntity> pendidikanEntities = pendidikanRepository.findAll();
		return pendidikanEntities;
	}
	
//	save
	@PostMapping("/post-pendidikan")
	public ResponseEntity<?> insertPendidikan(@RequestBody PendidikanDto dto){
		
		PendidikanEntity pendidikanEntity = new PendidikanEntity();
		PersonEntity personEntity = personRepository.findById(dto.getPersonId()).get();
		
		pendidikanEntity.setInstitusi(dto.getInstitusi());
		pendidikanEntity.setLevel(dto.getLevel());
		pendidikanEntity.setTahunMasuk(dto.getTahunMasuk());
		pendidikanEntity.setTahunLulus(dto.getTahunLulus());
		pendidikanEntity.setPersonEntity(personEntity);
		pendidikanRepository.save(pendidikanEntity);
		return ResponseEntity.ok(pendidikanEntity);
	}
//	update
	@PutMapping("/update-pendidikan/{idPendidikan}")
	public ResponseEntity<?> update(@PathVariable Integer idPendidikan, @RequestBody PendidikanDto dto){
		PendidikanEntity pendidikanEntity = pendidikanRepository.findById(idPendidikan).get();
		pendidikanEntity.setLevel(dto.getLevel());
		pendidikanEntity.setInstitusi(dto.getInstitusi());
		pendidikanEntity.setLevel(dto.getLevel());
		pendidikanEntity.setTahunMasuk(dto.getTahunMasuk());
		pendidikanEntity.setTahunLulus(dto.getTahunLulus());
		pendidikanRepository.save(pendidikanEntity);
		return ResponseEntity.ok(pendidikanEntity);
	}
//	delete
	
	@DeleteMapping("/delete/{idPendidikan}")
	public ResponseEntity<?> delete(@PathVariable Integer idPendidikan){
		PendidikanEntity pendidikanEntity = pendidikanRepository.findById(idPendidikan).get();
		pendidikanRepository.delete(pendidikanEntity);
		return ResponseEntity.ok(pendidikanEntity);
	}
	
	
}
