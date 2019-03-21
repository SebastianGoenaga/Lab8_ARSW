package edu.eci.models;

import java.io.Serializable;

public class Car implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private String brand;
    
    public Car() {
	}
    
    public Car(String licensePlate, String brand) {
    	this.licensePlate = licensePlate;
    	this.brand = brand;
	}
    
    public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
