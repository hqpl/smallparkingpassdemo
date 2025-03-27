package com.hackathon.smallparkingpassdemo.passdomain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Repository
public interface ParkingPassRepository extends MongoRepository<ParkingPass, String> {
    List<ParkingPass> findByExpirationDateBefore(LocalDateTime expirationDate);

    @Query(value = "{ 'car_id' : ?0, 'expiration_date' : { $gt : ?1 } }", count = true)
    int countCustomQuery(String carId, LocalDateTime currentDate);

    Optional<List<ParkingPass>> findTop5ByCarIdOrderByCreationDateDesc(String carId);

    Optional<ParkingPass> findFirstByCarIdOrderByCreationDateDesc(String carId);

}
