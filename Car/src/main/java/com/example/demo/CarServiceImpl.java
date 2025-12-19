package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {
	private final CarRepository carRepository;
	Random rand = new Random();
	private ArrayList<String> carImageArray = new ArrayList<String>(
			Arrays.asList("https://images.pexels.com/photos/810357/pexels-photo-810357.jpeg",
					"https://images.pexels.com/photos/244206/pexels-photo-244206.jpeg",
					"https://images.pexels.com/photos/10256429/pexels-photo-10256429.jpeg",
					"https://images.pexels.com/photos/29352558/pexels-photo-29352558.jpeg",
					"https://images.pexels.com/photos/27019870/pexels-photo-27019870.jpeg",
					"https://images.pexels.com/photos/12866903/pexels-photo-12866903.jpeg",
					"https://images.pexels.com/photos/193991/pexels-photo-193991.jpeg",
					"https://images.pexels.com/photos/193995/pexels-photo-193995.jpeg",
					"https://images.pexels.com/photos/4096527/pexels-photo-4096527.jpeg",
					"https://images.pexels.com/photos/9162106/pexels-photo-9162106.jpeg",
					"https://images.pexels.com/photos/10358231/pexels-photo-10358231.jpeg"));

	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public Car createCar(Car car) {
		if (car.getImage() == null || car.getImage().isEmpty()) {
			car.setImage(carImageArray.get(rand.nextInt(carImageArray.size())));
		}
		Car car_ =  carRepository.save(car);
		System.out.println("CAR " + car + "\n car " + car_ );
		return car_;
	}

	@Override
	public Car getCarById(Long id) {
		return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
	}

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public Car updateCar(Long id, Car car) {
		Car existing = getCarById(id);
		if (car.getImage() == null || car.getImage().isEmpty()) {
			car.setImage(carImageArray.get(rand.nextInt(carImageArray.size())));
		}
		existing.setBrand(car.getBrand());
		existing.setModel(car.getModel());
		existing.setYear(car.getYear());
		existing.setPrice(car.getPrice());
		existing.setImage(car.getImage());
		return carRepository.save(existing);
	}

	@Override
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

	@Override
	public List<String> getAllCarImage() {
//		List<String> data = new ArrayList<>();
//		List<Car> dataCar = carRepository.findAll();
//		for (Car car : dataCar) {
//			data.add(car.getImage());
//		}
		return carImageArray;
	}
}
