package com.upc.UserRolePayment.repository;

import com.upc.UserRolePayment.model.entity.Roles;
import com.upc.UserRolePayment.utils.RolesName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName (RolesName name);
}
