package com.upc.UserRolePayment.model.entity;

import com.upc.UserRolePayment.utils.RolesName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolesName name;

    public Roles(RolesName name) {
        this.name = name;
    }
}
