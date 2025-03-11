package com.hackathon.smallparkingpassdemo.notificationdomain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hackathon.smallparkingpassdemo.passdomain.ParkingPass;
import com.hackathon.smallparkingpassdemo.passdomain.ParkingPassService;

import java.util.List;

@Component
public class ExpirationNotificationScheduler {

    private final ParkingPassService parkingPassService;
    private final NotificationService notificationService; // Your notification service

    public ExpirationNotificationScheduler(ParkingPassService parkingPassService, NotificationService notificationService) {
        this.parkingPassService = parkingPassService;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "* * 10 * * *") // Runs every day at 10:00 local time
    public void checkExpirations() {
        System.out.println("CRON: checking for expiring parking passes.");
        // Get list of Parking Passes that Expiration Date is within given parameter
        List<ParkingPass> objectsToNotify = parkingPassService.findByExpirationDate();

        // Iterate each Parking card and pass it to the notification service
        for (ParkingPass pass : objectsToNotify) {
            notificationService.sendExpirationNotification(pass);
        }
    }
}
