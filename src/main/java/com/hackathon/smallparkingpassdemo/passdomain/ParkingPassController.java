package com.hackathon.smallparkingpassdemo.passdomain;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.smallparkingpassdemo.config.UserUtils;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/20250227/pass")
public class ParkingPassController {
    
    private final ParkingPassService parkingPassService;

    private final UserUtils userUtils;

    public ParkingPassController(ParkingPassService parkingPassService, UserUtils userUtils) {
        this.parkingPassService = parkingPassService;
        this.userUtils = userUtils;
    }

    @PostMapping("/generate/{carId}") // Create new parking card for given Car and email
    public ResponseEntity<ParkingPass> generateParkingPass(@PathVariable String carId) {
        String ownerEmail = userUtils.getEmail();
        if (ownerEmail.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        ParkingPass newParkingPass = parkingPassService.generateParkingPass(carId, ownerEmail);
        if (newParkingPass == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(newParkingPass);
    }

    @GetMapping() // Get all parking cards of given user email
    public List<ParkingPass> getMethodName() {
        String ownerEmail = userUtils.getEmail();
        if (ownerEmail.isBlank()) {
            return null;
        }
        return parkingPassService.getParkingPasses(ownerEmail);
    }
    
}
