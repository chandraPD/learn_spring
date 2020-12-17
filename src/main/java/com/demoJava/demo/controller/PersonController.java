package com.demoJava.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoJava.demo.dto.BiodataDto;
import com.demoJava.demo.dto.DetailBiodataDto;
import com.demoJava.demo.dto.PersonDto;
import com.demoJava.demo.dto.StatusMessageDto;
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
	
	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id){
//		PersonEntity personEntity = new PersonEntity();
		PersonDto dto = new PersonDto();
		dto.setFirstName(personRepository.findFirstNameById(id));
		return ResponseEntity.ok(dto.getFirstName());
	}
	
	@PostMapping("/post-person")
	public ResponseEntity<?> insertPerson(@RequestBody BiodataDto dto){
			
		PersonEntity personEntity = convertToPersonEntity(dto);
		personRepository.save(personEntity);
		return ResponseEntity.ok(personEntity);
	}
	

	@PostMapping("/post-person-status")
	public ResponseEntity<?> insertPerson2(@RequestBody BiodataDto dto){
			
		if(dto.getNik().length() != 16) {
			StatusMessageDto<PersonEntity> result = new StatusMessageDto<>();
			result.setMessage("Nik Harus berisi 16 karakter");
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return ResponseEntity.badRequest().body(result);
		}else {
			PersonEntity personEntity = convertToPersonEntity(dto);
			StatusMessageDto<PersonEntity> result = new StatusMessageDto<>();
						
			personRepository.save(personEntity);
			result.setMessage("Berhasil");
			result.setStatus(HttpStatus.OK.value());
			result.setData(personEntity);
			return ResponseEntity.ok(result);
		}
		
	}
	
	
	@GetMapping("/get-biodata")
	public ResponseEntity<?> getAllBiodata(){
		List<DetailBiodataEntity> detailBiodataEntities = detailBiodataRepository.findAll();
		return ResponseEntity.ok(detailBiodataEntities);
	}
	
	@PostMapping("/post-biodata")
	public ResponseEntity<?> insertBiodata(@RequestBody BiodataDto dto){
		DetailBiodataEntity detailBiodataEntity = convertToDetailBiodataEntity(dto);
		detailBiodataRepository.save(detailBiodataEntity);
		return ResponseEntity.ok(detailBiodataEntity);
	}
	

	@PostMapping("/post-detail-person")
	public ResponseEntity<?> insertDetail(@RequestBody DetailBiodataDto dto){
		
		if(dto.getUsia() < 17 || dto.getUsia() > 50) {
			StatusMessageDto<PersonEntity> result = new StatusMessageDto<>();
			result.setMessage("Usia harus Lebih Besar dari 17 Tahun dan Lebih kecil dari 50 Tahun");
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return ResponseEntity.ok(result);
		}else {
			StatusMessageDto<PersonEntity> result = new StatusMessageDto<>();
			DetailBiodataEntity detailBiodataEntity = new DetailBiodataEntity();
			PersonEntity personEntity = personRepository.findById(dto.getPersonId()).get();
			detailBiodataEntity.setDomisili(dto.getDomisili());
			detailBiodataEntity.setHobi(dto.getHobi());
			detailBiodataEntity.setJenisKelamin(dto.getJenisKelamin());
			detailBiodataEntity.setTanggalLahir(dto.getTanggalLahir());
			detailBiodataEntity.setUsia(dto.getUsia());
			detailBiodataEntity.setPersonEntity(personEntity);
			detailBiodataRepository.save(detailBiodataEntity);
			
			result.setMessage("Berhasil");
			result.setStatus(HttpStatus.OK.value());
			result.setData(personEntity);
			return ResponseEntity.ok(result);
		}
		
		
	}
	
//	UPDATE DATA
	@PutMapping("/update-person/{idPerson}")
	public ResponseEntity<?> update(@PathVariable Integer idPerson, @RequestBody PersonDto dto){
		PersonEntity personEntity = personRepository.findById(idPerson).get();
		personEntity.setFirstName(dto.getFirstName());
		personEntity.setLastName(dto.getLastName());
		personRepository.save(personEntity);
		return ResponseEntity.ok(personEntity);
	}
	
	@PutMapping("/update-biodata/{idBiodata}")
	public ResponseEntity<?> update(@PathVariable Integer idBiodata, @RequestBody DetailBiodataDto dto){
		DetailBiodataEntity detailBiodataEntity = detailBiodataRepository.findById(idBiodata).get();
		PersonEntity personEntity = personRepository.findById(dto.getPersonId()).get();
		detailBiodataEntity.setDomisili(dto.getDomisili());
		detailBiodataEntity.setHobi(dto.getHobi());
		detailBiodataEntity.setJenisKelamin(dto.getJenisKelamin());
		detailBiodataEntity.setTanggalLahir(dto.getTanggalLahir());
		detailBiodataEntity.setUsia(dto.getUsia());
		detailBiodataEntity.setPersonEntity(personEntity);
		detailBiodataRepository.save(detailBiodataEntity);
		return ResponseEntity.ok(detailBiodataEntity);
	}
	
	@PostMapping("/insert-all")
	public ResponseEntity<?> insertAll(@RequestBody BiodataDto dto){
		PersonEntity personEntity = convertToPersonEntity(dto);

		DetailBiodataEntity detailBiodataEntity = convertToDetailBiodataEntity(dto);
		
		personRepository.save(personEntity);
		detailBiodataEntity.setPersonEntity(personEntity);
		detailBiodataRepository.save(detailBiodataEntity);
		return ResponseEntity.ok(detailBiodataEntity);
	}
	
//	Delete Data
	@DeleteMapping("/delete/{idPerson}")
	public ResponseEntity<?> delete(@PathVariable Integer idPerson){
		PersonEntity personEntity = personRepository.findById(idPerson).get();
		personRepository.delete(personEntity);
		return ResponseEntity.ok(personEntity);
	}
	
//	METHOD CONVERT
	public PersonEntity convertToPersonEntity(BiodataDto dto) {
		PersonEntity personEntity = new PersonEntity();
		
		personEntity.setFirstName(dto.getFirstName());
		personEntity.setLastName(dto.getLastName());
		personEntity.setNik(dto.getNik());
		return personEntity;
	}
	
	public DetailBiodataEntity convertToDetailBiodataEntity(BiodataDto dto) {
//		PersonEntity personEntity = personRepository.findById(idPerson).get();
		DetailBiodataEntity detailBiodataEntity = new DetailBiodataEntity();
		
		detailBiodataEntity.setDomisili(dto.getDomisili());
		detailBiodataEntity.setHobi(dto.getHobi());
		detailBiodataEntity.setJenisKelamin(dto.getJenisKelamin());
		detailBiodataEntity.setTanggalLahir(dto.getTanggalLahir());
		detailBiodataEntity.setUsia(dto.getUsia());
		
		return detailBiodataEntity;
	}
	
}
