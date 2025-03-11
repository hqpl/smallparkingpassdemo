package com.hackathon.smallparkingpassdemo.notificationdomain;

import org.springframework.stereotype.Service;

import com.hackathon.smallparkingpassdemo.passdomain.ParkingPass;

@Service
public class NotificationService {

    public void sendExpirationNotification(ParkingPass pass) {
        System.out.println(pass.getExpirationDate());
    }

}
