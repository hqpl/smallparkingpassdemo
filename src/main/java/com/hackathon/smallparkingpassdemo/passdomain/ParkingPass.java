package com.hackathon.smallparkingpassdemo.passdomain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "parking_passes")
public class ParkingPass {
    
    @Id
    private String id;

    @Field(name = "car_id")
    private String carId;

    @Field(name = "creation_date")
    private LocalDateTime creationDate;

    @Field(name = "expiration_date")
    private LocalDateTime expirationDate;

    private boolean isActive;

    public ParkingPass() {
        this.creationDate = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusDays(180);
        this.isActive = true;
    }

    public boolean isNotExpired() {
        return LocalDateTime.now().isBefore(this.expirationDate);
    }
}
