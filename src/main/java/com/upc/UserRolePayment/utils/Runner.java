package com.upc.UserRolePayment.utils;
import com.upc.UserRolePayment.model.entity.Roles;
import com.upc.UserRolePayment.model.entity.User;
import com.upc.UserRolePayment.repository.RolesRepository;
import com.upc.UserRolePayment.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class Runner implements CommandLineRunner{
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public Runner(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola Mundo!");
        if (this.rolesRepository.count() == 0) {
            this.rolesRepository.saveAll(List.of(
                    new Roles(RolesName.ADMIN),
                    new Roles(RolesName.USER),
                    new Roles(RolesName.OTHER)
            ));
         }
        if (this.userRepository.count() == 0) {
            var encoders = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            this.userRepository.saveAll(List.of(
                    new User("admin", encoders.encode("admin123"), List.of(this.rolesRepository.findByName(RolesName.ADMIN).get())),
                    new User("user",encoders.encode("user123"), List.of(this.rolesRepository.findByName(RolesName.USER).get())),
                    new User("other", encoders.encode("other123"), List.of(this.rolesRepository.findByName(RolesName.OTHER).get()))
            ));
        }
    }
}
