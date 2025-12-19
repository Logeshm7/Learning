package com.example.demo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public interface CarService {
	AtomicLong idGenerator = new AtomicLong(1L);
    Car createCar(Car car);
    Car getCarById(Long id);
    List<Car> getAllCars();
    List<String> getAllCarImage();
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);
    default Long getAutogenarateId() {
    	return idGenerator.getAndIncrement();
    }
}
