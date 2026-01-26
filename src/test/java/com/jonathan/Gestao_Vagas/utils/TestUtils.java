package com.jonathan.Gestao_Vagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

//Irá transformar um objeto num JSON
public class TestUtils {
    public static String objectToJSON(Object obj){
        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    //Irá criar o tolen para a realização do test de integração
    public static String generateToken(UUID idCompany, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expireIn = Instant.now().plus(Duration.ofHours(2)); //Expiração do token
        var token = JWT.create().withIssuer("javagas") //withExpiresAt(Instant.now().plus(Duration.ofHours(2))) -> token Expira
                .withSubject(idCompany.toString())
                .withExpiresAt(expireIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);
        return token;
    }
}
