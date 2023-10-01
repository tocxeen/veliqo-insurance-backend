package com.veliqo.codeChallenge.user;

import org.springframework.stereotype.Component;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Component
public class UserDTOConverter {

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setRoles(userDTO.getRoles());
        user.setStatus(userDTO.getStatus());
        return user;
    }

    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setStatus(user.getStatus());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
