package com.hackathon.smallparkingpassdemo.cardomain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.smallparkingpassdemo.config.UserUtils;
import com.hackathon.smallparkingpassdemo.passdomain.ParkingPass;
import com.hackathon.smallparkingpassdemo.passdomain.ParkingPassService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/20250327/carpass")
public class CarPassController {

    private final CarService carService;
    private final ParkingPassService parkingPassService;
    private final UserUtils userUtils;
    

    public CarPassController(CarService carService, ParkingPassService parkingPassService, UserUtils userUtils) {
        this.carService = carService;
        this.parkingPassService = parkingPassService;
        this.userUtils = userUtils;
    }

    @GetMapping() // Gets user email from authentication and lists cars and latest active parking cards
    public ResponseEntity<List<CarPassResponseDTO>> getCarPassByEmailFromAuth() {
        String ownerEmail = userUtils.getEmail(); // Get email
        if (ownerEmail.isBlank()) {
            return ResponseEntity.noContent().build();
        }

        Optional<List<Car>> cars = carService.getCarsByEmail(ownerEmail); // Get cars by email
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        List<CarPassResponseDTO> carPassResponseDTOs = new ArrayList<>(); // Iterate through each car
        for (Car car : cars.get()) {
            CarPassResponseDTO carPassDTO = carService.getCarPassResponseDTO(car);

            if (carPassDTO != null) {
                Optional<ParkingPass> parkingPassOptional = parkingPassService.getOneParkingPassByCarIdLatest(carPassDTO.getCarId());
                if(!parkingPassOptional.isEmpty()) {
                    ParkingPass parkingPass = parkingPassOptional.get();
                    carPassDTO.setPassExpirationDate(parkingPass.getExpirationDate());
                    carPassDTO.setPassCreationDate(parkingPass.getCreationDate());
                    carPassDTO.setPassId(parkingPass.getId());
                    carPassDTO.setPassActive(parkingPass.isActive());
                }
            }
            
            carPassResponseDTOs.add(carPassDTO);

        }

        return ResponseEntity.ok(carPassResponseDTOs);
    }   
    
}
