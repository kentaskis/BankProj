package com.project.bankproj.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class GenerateJWTKeys {

    public static void main(String[] args) {
        System.out.println(generateJWTKey());
        System.out.println(generateJWTKey());
    }

    private static String generateJWTKey() {
        return Encoders.BASE64.encode(
                Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
    }
}