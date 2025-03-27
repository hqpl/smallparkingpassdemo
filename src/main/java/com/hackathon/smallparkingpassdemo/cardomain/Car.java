package com.hackathon.smallparkingpassdemo.cardomain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@Document(collection = "cars")
public class Car {

    @Id
    private String id;

    @Field(name = "manufacturer")
    private String manufacturer;

    @Indexed(unique = false)
    @Field(name = "owner_email")
    private String ownerEmail;

    @Indexed(unique = false)
    @Field(name = "plates")
    private String plates;

    @Field(name = "creation_date")
    private LocalDateTime creationDate;

    @Field(name = "is_active")
    private boolean isActive = true;

    @Field(name = "is_not_demised")
    private boolean isNotDemised = true;

    @Field(name = "car_log")
    private List<LogEntry> carLog;


    public Car() {
        this.creationDate = LocalDateTime.now();
    }

    public void addLogEntry (String message) {
        if (this.carLog == null) {
            this.carLog = new ArrayList<>();
            throw new NoSuchElementException("List is empty when it should contain at least 1 element - Created.");
        }
        this.carLog.add(new LogEntry(message));
    
    }
}
