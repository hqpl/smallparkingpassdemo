package com.hackathon.smallparkingpassdemo.cardomain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    Optional<List<Car>> findByOwnerEmail(String email);
}
