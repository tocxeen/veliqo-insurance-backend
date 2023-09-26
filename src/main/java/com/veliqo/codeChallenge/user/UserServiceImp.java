package com.veliqo.codeChallenge.user;


import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.user.models.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private UserDTOConverter converter;


    @Override
    public Optional<UserDTO> saveUser(User user) {
        user.setStatus(Status.INACTIVE);
        userRepository.findByEmail(user.getEmail()).ifPresent(userData -> {
            throw new RecordExistException(String.format("User with email %s already exists", userData.getEmail()));
        });
        return Optional.of(converter.toDTO(userRepository.save(user)));
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        Optional<User> userDTO = userRepository.findByEmail(email);
        if(userDTO.isPresent()){
            return Optional.of(converter.toDTO(userDTO.get()));
        }
        throw new RecordNotFoundException(String.format("User not %s found!",email));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> userDTO = userRepository.findAll();
        List<UserDTO> res = new ArrayList<>();
        if(userDTO.size()>0){
            userDTO.forEach((user)->{
                res.add(converter.toDTO(user));
            });
            return res;
        }
        throw new RecordNotFoundException(String.format("No data found"));
    }


    @Override
    @Transactional
    public Optional<UserDTO> updateStatus(String email){
        Status tempStatus = Status.INACTIVE;
        Optional<UserDTO> userDTO =  findUserByEmail(email);
        if(userDTO!=null){
            Status status = userDTO.get().getStatus();
            if(status.equals(Status.INACTIVE)){tempStatus = Status.ACTIVE;}
            int hasExecuted = userRepository.updateStatusByEmail(tempStatus,email);
            if(hasExecuted==1) return findUserByEmail(email);
            throw new RuntimeException("Failed to update status");
        }
        throw new RecordNotFoundException(String.format("Failed to update %s 's status!", email));
    }

    @Override
    @Transactional
    public int updatePassword(String password, String email){
        Optional<User> userDTO = userRepository.findByEmail(email);
        if(userDTO !=null){return userRepository.updatePasswordByEmail(password,email);}
        throw new RecordNotFoundException(String.format("Failed to update %s 's status!", email));
    }



}
