package com.hackathon.smallparkingpassdemo.cardomain;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class LogEntry {
    private String action;
    private LocalDateTime timestamp;

    public LogEntry() {
    }

    public LogEntry(String action) {
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public LogEntry(String action, LocalDateTime timestamp) {
        this.action = action;
        this.timestamp = timestamp;
    }
    
}
