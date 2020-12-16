package com.demoJava.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pendidikan_entity")
public class PendidikanEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "level", length = 50, nullable = false)
	private String level;
	
	@Column(name = "institusi", length = 50 , nullable = false)
	private String institusi;
	
	@Column(name = "tahunMasuk", length = 10, nullable = false)
	private String tahunMasuk;
	
	@Column(name = "tahunLulus", length = 10, nullable = false )
	private String tahunLulus;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private PersonEntity personEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getInstitusi() {
		return institusi;
	}

	public void setInstitusi(String institusi) {
		this.institusi = institusi;
	}

	public String getTahunMasuk() {
		return tahunMasuk;
	}

	public void setTahunMasuk(String tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}

	public String getTahunLulus() {
		return tahunLulus;
	}

	public void setTahunLulus(String tahunLulus) {
		this.tahunLulus = tahunLulus;
	}

	public PersonEntity getPersonEntity() {
		return personEntity;
	}

	public void setPersonEntity(PersonEntity personEntity) {
		this.personEntity = personEntity;
	}

	
	public PendidikanEntity() {
		super();
	}

	public PendidikanEntity(Integer id, String level, String institusi, String tahunMasuk, String tahunLulus,
			PersonEntity personEntity) {
		super();
		this.id = id;
		this.level = level;
		this.institusi = institusi;
		this.tahunMasuk = tahunMasuk;
		this.tahunLulus = tahunLulus;
		this.personEntity = personEntity;
	}
	
	

}
