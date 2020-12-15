package com.demoJava.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoJava.demo.dto.DetailBiodataDto;
import com.demoJava.demo.dto.PersonDto;
import com.demoJava.demo.entity.DetailBiodataEntity;
import com.demoJava.demo.entity.PersonEntity;
import com.demoJava.demo.repository.DetailBiodataRepository;
import com.demoJava.demo.repository.PersonRepository;

@RestController
@RequestMapping("/person") // localhost:8080/person
public class PersonController {
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	DetailBiodataRepository detailBiodataRepository;

	@GetMapping("/get-all")
	public List<PersonEntity> getPerson() {
		List<PersonEntity> personEntities = personRepository.findAll();
//		personEntities = personRepository.findByFirstName("ajeng"); contoh
		return personEntities;
	}
	
	@GetMapping("/respon-entity")
	public ResponseEntity<?> getAll(){
		List<PersonEntity> personEntities = personRepository.findAll();
		return ResponseEntity.ok(personEntities);
	}
	
	@PostMapping("/post-person")
	public ResponseEntity<?> insertPerson(@RequestBody PersonDto dto){
		PersonEntity personEntity = new PersonEntity();
		
		personEntity.setFirstName(dto.getFirstName());
		personEntity.setLastName(dto.getLastName());
		personRepository.save(personEntity);
		return ResponseEntity.ok(personEntity);
	}
	
	
	@GetMapping("/get-biodata")
	public ResponseEntity<?> getAllBiodata(){
		List<DetailBiodataEntity> detailBiodataEntities = detailBiodataRepository.findAll();
		return ResponseEntity.ok(detailBiodataEntities);
	}
	
	@PostMapping("/post-biodata")
	public ResponseEntity<?> insertBiodata(@RequestBody DetailBiodataDto dto){
		DetailBiodataEntity detailBiodataEntity = new DetailBiodataEntity();
		
		detailBiodataEntity.setDomisili(dto.getDomisili());
		detailBiodataEntity.setHobi(dto.getHobi());
		detailBiodataEntity.setJenisKelamin(dto.getJenisKelamin());
		detailBiodataEntity.setTanggalLahir(dto.getTanggalLahir());
		detailBiodataEntity.setUsia(dto.getUsia());

		detailBiodataRepository.save(detailBiodataEntity);
		return ResponseEntity.ok(detailBiodataEntity);
	}
}
