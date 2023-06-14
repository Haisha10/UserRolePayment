package com.upc.UserRolePayment.security;

import com.upc.UserRolePayment.model.entity.Roles;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor

public class SecurityRoles implements  GrantedAuthority{
      private final Roles roles;
    @Override
    public String getAuthority() {
        return roles.getName().toString();
    }
}
