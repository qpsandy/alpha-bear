package com.haah.bear.core.pojo;

public class HotelPojo {

	private String name;
	private String addr;
	private String zipCode;
	private String telephone;
	private String price;
	
	public HotelPojo(String name, String addr, String zipCode,
			String telephone, String price) {
		super();
		this.name = name;
		this.addr = addr;
		this.zipCode = zipCode;
		this.telephone = telephone;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
