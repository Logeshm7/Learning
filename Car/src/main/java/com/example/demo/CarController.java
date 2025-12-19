package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	// CREATE
	@PostMapping
	public ResponseEntity<ApiResponse<Car>> createCar(@RequestBody Car car) {
		Car saved = carService.createCar(car);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, "Car created successfully", saved));
	}

	// CREATE
	@PostMapping("/addAllCar")
	public ResponseEntity<ApiResponse<String>> createCarArray(@RequestBody List<Car> carArray) {
		for (Car car : carArray) {
			carService.createCar(car);
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, "Car array created successfully","" ));
	}

	// READ ALL
	@GetMapping
	public ResponseEntity<ApiResponse<List<Car>>> getAllCars() {
		return ResponseEntity.ok(new ApiResponse<>(true, "Cars fetched successfully", carService.getAllCars()));
	}

	// READ ALL
	@GetMapping("/getAllCarImage")
	public ResponseEntity<ApiResponse<List<String>>> getAllCarImage() {
		return ResponseEntity
				.ok(new ApiResponse<>(true, "Cars Image fetched successfully", carService.getAllCarImage()));
	}

	// READ BY IDpo
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Car>> getCarById(@PathVariable("id") Long id) {
		Car car = carService.getCarById(id);
		return ResponseEntity.ok(new ApiResponse<>(true, "Car found", car));
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Car>> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
		Car updated = carService.updateCar(id, car);
		return ResponseEntity.ok(new ApiResponse<>(true, "Car updated successfully", updated));
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteCar(@PathVariable("id") Long id) {
		carService.deleteCar(id);
		return ResponseEntity.ok(new ApiResponse<>(true, "Car deleted successfully", ""));
	}

}