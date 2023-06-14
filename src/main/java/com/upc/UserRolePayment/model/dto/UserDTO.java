package com.upc.UserRolePayment.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.upc.UserRolePayment.model.entity.Roles;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    Long id;
    String username;
    List<Roles> role;
    Double payment;
}
