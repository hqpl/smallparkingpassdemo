package com.hackathon.smallparkingpassdemo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hackathon.smallparkingpassdemo.cardomain.Car;
import com.hackathon.smallparkingpassdemo.cardomain.CarPassResponseDTO;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.createTypeMap(Car.class, CarPassResponseDTO.class)
            .addMapping(Car::getId, CarPassResponseDTO::setCarId)
            .addMapping(Car::getOwnerEmail, CarPassResponseDTO::setCarOwnerEmail)
            .addMapping(Car::getManufacturer, CarPassResponseDTO::setCarManufacturer)
            .addMapping(Car::getPlates, CarPassResponseDTO::setCarPlates)
            .addMapping(Car::getCreationDate, CarPassResponseDTO::setCarCreationDate)
            .addMapping(Car::isActive, CarPassResponseDTO::setCarActive)
            .addMapping(Car::isNotDemised, CarPassResponseDTO::setCarNotDemised);

        return modelMapper;
    }
}
