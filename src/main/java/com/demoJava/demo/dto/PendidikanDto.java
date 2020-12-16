package com.demoJava.demo.dto;

public class PendidikanDto {
	private Integer id;
	private String level;
	private String institusi;
	private String tahunMasuk;
	private String tahunLulus;
	private Integer personId;
	
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
	public void setTahunLulus(String tahunKeluar) {
		this.tahunLulus = tahunKeluar;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	
}
