package com.hackathon.smallparkingpassdemo.tokendomain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class JwtTokenUtil {

    public String generateNewToken(String entity) {
        try {
        // Convert string to bytes for serialization
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(entity);
        
        // Convert to Base64 encoded string for safe transport
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    } catch (IOException e) {
        throw new RuntimeException("Failed to serialize token", e);
    }
    }

}
