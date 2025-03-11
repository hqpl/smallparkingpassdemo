package com.hackathon.smallparkingpassdemo.tokendomain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/token")
public class TokenController {

    private JwtTokenUtil jwtTokenUtil;

    public TokenController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/authenticate")
    public String generateAuthenticationToken(@RequestBody String entity) {
        
        return jwtTokenUtil.generateNewToken(entity);
    }
    
    
}
