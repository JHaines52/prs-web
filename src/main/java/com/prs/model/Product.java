package com.prs.model;

import jakarta.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String partNumber;
	private String name;
	private float price;
	private String unit;
	private String photoPath;

	@ManyToOne
	@JoinColumn(name = "VendorId")
	private Vendor vendor;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String partnumber, String name, float price, String unit, String photopath, Vendor vendor) {
		super();
		this.id = id;
		this.partNumber = partnumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photopath;
		this.vendor = vendor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartnumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotopath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", partnumber=" + partNumber + ", name=" + name + ", price=" + price + ", unit="
				+ unit + ", photopath=" + photoPath + ", vendor=" + vendor + "]";
	}
	
}