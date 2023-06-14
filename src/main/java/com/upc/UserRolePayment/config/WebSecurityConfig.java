package com.upc.UserRolePayment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig {
 

     @Bean
     public     PasswordEncoder passwordEncoder() {
         //return NoOpPasswordEncoder.getInstance(); // No apto para producción
         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
     }
       // para encriptar
     public static  void main (String[] args) {
         System.out.println("Pass: " + new BCryptPasswordEncoder().encode("claveUpc"));
     }
    
}
