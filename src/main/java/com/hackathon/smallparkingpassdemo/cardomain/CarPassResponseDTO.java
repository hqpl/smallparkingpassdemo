package com.hackathon.smallparkingpassdemo.cardomain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CarPassResponseDTO {
    
    // Car part
    private String carId;
    private String carOwnerEmail;
    private String carManufacturer;
    private String carPlates;
    private LocalDateTime carCreationDate;
    private boolean isCarActive;
    private boolean isCarNotDemised;


    // Parking Pass part
    private String passId;
    private LocalDateTime passCreationDate;
    private LocalDateTime passExpirationDate;    
    private boolean isPassActive;
    
}
