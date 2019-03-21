package edu.eci.persistences.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.eci.models.Car;

@Repository
public interface IUCarRepository extends DAO<Car, String> {
	Car getCarByLicense(String licensePlate);
}
