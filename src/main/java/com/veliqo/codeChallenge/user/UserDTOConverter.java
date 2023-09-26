package com.veliqo.codeChallenge.user;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Component
public class UserDTOConverter {

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        return user;
    }

    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setStatus(user.getStatus());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
