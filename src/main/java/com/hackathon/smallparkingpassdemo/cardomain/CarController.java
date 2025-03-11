package com.hackathon.smallparkingpassdemo.cardomain;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.hackathon.smallparkingpassdemo.config.UserUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/20250227/car")
public class CarController {
    private final CarService carService;

    private final UserUtils userUtils;


    public CarController(CarService carService, UserUtils userUtils) {
        this.carService = carService;
        this.userUtils = userUtils;
    }

    @GetMapping() // Pull information about Car, email is gathered from Authentication.getName()
    public List<Car> getCarsByEmail() {
        String ownerEmail = userUtils.getEmail();
        if (ownerEmail.isBlank()) {
            return null;
        }
        return carService.getCarsByEmail(ownerEmail).orElse(List.of());
    }

    @PostMapping() // Saves car to the database
    public ResponseEntity<Car> addNewCar(@RequestBody Car newCar) {
        
        String ownerEmail = userUtils.getEmail();
        if (!ownerEmail.isBlank()) {
            newCar.setOwnerEmail(ownerEmail);
            carService.saveCar(newCar);
            return ResponseEntity.ok(newCar);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{carId}/demise")
    public ResponseEntity<Car> demiseCar(@PathVariable String carId) {
        String ownerEmail = userUtils.getEmail();
        if (carService.demiseCar(carId, ownerEmail)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{carId}/deactivate")
    public ResponseEntity<Car> deactivateCar(@PathVariable String carId) {        
        String ownerEmail = userUtils.getEmail();
        if (carService.changeCarStatus(carId, ownerEmail, false)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PatchMapping("/{carId}/activate")
    public ResponseEntity<Car> activateCar(@PathVariable String carId) {        
        String ownerEmail = userUtils.getEmail();
        if (carService.changeCarStatus(carId, ownerEmail, true)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    
    
}
