package com.veliqo.codeChallenge.user;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> saveUser(User user);

    Optional<UserDTO> findUserByUsername(String email);

    List<UserDTO> findAllUsers();

    Optional<UserDTO> updateStatus(String email);

    @Transactional
    int updatePassword(String password, String email);

}
