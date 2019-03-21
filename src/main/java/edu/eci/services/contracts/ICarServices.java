package edu.eci.services.contracts;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.models.Car;

@Service
public interface ICarServices {
	
	List<Car> list();
	Car create(Car car);
	Car get(String licensePlate);
	Car getByLicense(String licensePlate);
	void delete(Car car);
	void update(Car car);

}
