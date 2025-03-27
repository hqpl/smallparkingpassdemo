package com.hackathon.smallparkingpassdemo.cardomain;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    

    public CarService(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<List<Car>> getCarsByEmail(String email) {
        return carRepository.findByOwnerEmail(email);
    }

    public Car getCarById(String carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if(!carOptional.isPresent()) {
            return null;
        }
        return carOptional.get();
    }

    public Car saveCar(Car newCar) {
        return carRepository.save(newCar);
    }


    // Activate (true) or deactivate(false) Car status based on action
    public boolean changeCarStatus(String carId, String email, boolean action) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if(!carOptional.isPresent()) {
            return false;
        }
        Car existingCar = carOptional.get();
        if(existingCar.getOwnerEmail().equals(email)) {
            existingCar.setActive(action);
            existingCar.addLogEntry(email + " changed status to " + action + ".");
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }

    public boolean demiseCar(String carId, String email) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if(!carOptional.isPresent()) {
            return false;
        }
        Car existingCar = carOptional.get();
        System.out.println(existingCar.getId());
        if(existingCar.getOwnerEmail().equals(email)) {
            existingCar.setNotDemised(false);
            existingCar.addLogEntry(email + "marked this car as demised.");
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }


    public CarPassResponseDTO getCarPassResponseDTO(Car car) {
        return modelMapper.map(car, CarPassResponseDTO.class);
    }


}
