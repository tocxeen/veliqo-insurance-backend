package com.veliqo.codeChallenge.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.user.models.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Author Richard K Chifamba on 10/1/2023
 **/
@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDTOConverter converter;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(User userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "User Added Successfully";
    }


    public Optional<UserDTO> saveUser(User user) {
        user.setStatus(Status.INACTIVE);
        user.setPassword(encoder.encode("pass"));
        userRepository.findByUsername(user.getUsername()).ifPresent(userData -> {
            throw new RecordExistException(String.format("User with email %s already exists", userData.getUsername()));
        });
        return Optional.of(converter.toDTO(userRepository.save(user)));
    }

    public Optional<UserDTO> findUserByUsername(String email) {
        Optional<User> userDTO = userRepository.findByUsername(email);
        if(userDTO.isPresent()){
            return Optional.of(converter.toDTO(userDTO.get()));
        }
        throw new RecordNotFoundException(String.format("User not %s found!",email));
    }


    public List<UserDTO> findAllUsers() {
        List<User> userDTO = userRepository.findAll();
        List<UserDTO> res = new ArrayList<UserDTO>();
        if(userDTO.size()>0){
            userDTO.forEach((user)->{
                res.add(converter.toDTO(user));
            });
            return res;
        }
        throw new RecordNotFoundException(String.format("No data found"));
    }


    @Transactional
    public Optional<UserDTO> updateStatus(String email){
        Status tempStatus = Status.INACTIVE;
        Optional<UserDTO> userDTO =  findUserByUsername(email);
        if(userDTO!=null){
            Status status = userDTO.get().getStatus();
            if(status.equals(Status.INACTIVE)){tempStatus = Status.ACTIVE;}
            int hasExecuted = userRepository.updateStatusByEmail(tempStatus,email);
            if(hasExecuted==1) return findUserByUsername(email);
            throw new RuntimeException("Failed to update status");
        }
        throw new RecordNotFoundException(String.format("Failed to update %s 's status!", email));
    }


    @Transactional
    public int updatePassword(String password, String email){
        Optional<User> userDTO = userRepository.findByUsername(email);
        if(userDTO !=null){return userRepository.updatePasswordByEmail(password,email);}
        throw new RecordNotFoundException(String.format("Failed to update %s 's status!", email));
    }
}
