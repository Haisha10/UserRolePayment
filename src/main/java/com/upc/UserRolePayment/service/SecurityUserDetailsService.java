package com.upc.UserRolePayment.service;

import com.upc.UserRolePayment.repository.UserRepository;
import com.upc.UserRolePayment.security.SecurityUser;
import com.upc.UserRolePayment.utils.RolesName;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upc.UserRolePayment.model.dto.UserDTO;
import com.upc.UserRolePayment.model.entity.User;
import com.upc.UserRolePayment.model.entity.Roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUser = this.userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            return new SecurityUser(optUser.get());
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            List<Roles> roles = new ArrayList<Roles>();
            Double payment = 0.0;
            for (Roles role : user.getRoles()) {
                roles.add(role);
                if (role.getName().equals(RolesName.ADMIN)) {
                    payment = 100.0;
                } else if (role.getName().equals(RolesName.USER)) {
                    payment = 50.0;
                } else {
                    payment = 0.0;
                }
            }
            userDTO.setRole(roles);
            userDTO.setPayment(payment);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Transactional(readOnly = true)
    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        List<Roles> roles = new ArrayList<Roles>();
        for (Roles role : user.getRoles()) {
            roles.add(role);
        }
        userDTO.setRole(roles);
        Double payment = 0.0;
        userDTO.setPayment(payment);
        return userDTO;
    }
}
