package edu.eci.models;

import java.io.Serializable;

public class Car implements Serializable{

    private String licensePlate;
    private String brand;
    
    public Car() {
	}
    
    public Car(String licensePlate, String brand) {
    	this.licensePlate = licensePlate;
    	this.brand = brand;
	}
    
}
