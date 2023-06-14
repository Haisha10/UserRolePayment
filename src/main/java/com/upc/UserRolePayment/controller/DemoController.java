package com.upc.UserRolePayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.upc.UserRolePayment.model.dto.UserDTO;

import com.upc.UserRolePayment.service.SecurityUserDetailsService;
import com.upc.UserRolePayment.utils.RolesName;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
public class DemoController {

    @Autowired
    private SecurityUserDetailsService userService;

    @GetMapping("/users")
    public List<UserDTO> showUsers() {
        return userService.findAll();
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'OTHER')")
    public UserDTO showUser() {
        UserDTO user = userService.getByUsername(GetCurrentUsername());
        if(user.getRole().stream().anyMatch(role -> role.getName().equals(RolesName.ADMIN))){
            user.setPayment(100.0);
        }else if(user.getRole().stream().anyMatch(role -> role.getName().equals(RolesName.USER))){
            user.setPayment(50.0);
        }else{
            user.setPayment(0.0);
        }
        return user;
    }

    private String GetCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        /*
         * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         * String username = auth.getName();
         */
        return username;
    }

}
