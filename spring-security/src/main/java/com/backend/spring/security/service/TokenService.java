package com.backend.spring.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    private String code="";

    public TokenService() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey=keyGenerator.generateKey();
      code=  Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public String generateToken(String username) {

        Map<String,Object> claims=new HashMap<>();

        return Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+60*60*30))
                .signWith(getKey())
                .compact();


    }

    private Key getKey() {
        byte[] keyByte= Decoders.BASE64.decode(code);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
