package edu.eci.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.IUCarRepository;
import edu.eci.services.contracts.ICarServices;

@Component
public class CarServices implements ICarServices{
	
	@Autowired
	@Qualifier("CarPostgresRepository")
	private IUCarRepository carRepository;

	@Override
	public List<Car> list() {		
		return carRepository.findAll();
	}

	@Override
	public Car create(Car car) {
		if(carRepository.find(car.getLicensePlate()) != null)
            throw new RuntimeException("The car already exists");
        else
            carRepository.save(car);
        return car;
	}

	@Override
	public Car get(String licensePlate) {
		return carRepository.find(licensePlate);
	}

	@Override
	public Car getByLicense(String licensePlate) {		
		return carRepository.getCarByLicense(licensePlate);
	}

	@Override
	public void delete(Car car) {
		carRepository.delete(car);
		
	}

	@Override
	public void update(Car car) {
		carRepository.update(car);
		
	}
	
	
}
