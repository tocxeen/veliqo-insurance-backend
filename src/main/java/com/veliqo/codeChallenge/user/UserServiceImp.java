package com.veliqo.codeChallenge.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.veliqo.codeChallenge.applicant.Applicant;
import com.veliqo.codeChallenge.applicant.ApplicantDTO;
import com.veliqo.codeChallenge.exceptions.InvalidPasswordException;
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



    public Optional<UserDTO> saveUser(User user) {
        user.setStatus(Status.INACTIVE);
        user.setPassword(encoder.encode(user.getPassword()));
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
    public Optional<UserDTO> updateName(String name, String username){
        Optional<UserDTO> userDTO = findUserByUsername(username);
        if(userDTO.isPresent()){
            int hasExecuted = userRepository.updateName(name, username);
            if(hasExecuted==1) return findUserByUsername(username);
            throw new RuntimeException("Failed to update status");
        }
        throw new RecordNotFoundException(String.format("Failed to update %s 's status!", username));
    }


    @Transactional
    public int updatePassword(String email, String currentPassword, String newPassword){
        Optional<User> user = userRepository.findByUsername(email);
        if(user.isPresent()){
            if (!encoder.matches(currentPassword, user.get().getPassword())) {
                throw new InvalidPasswordException("The old password is incorrect.");
            }
            return userRepository.updatePasswordByEmail(email,encoder.encode(newPassword));
        }
        throw new RecordNotFoundException(String.format("Failed to update %s 's status!", email));
    }

    public NumberOfUsers getTotalUsers() {
        NumberOfUsers numberOfUsers = new NumberOfUsers();
        numberOfUsers.setAdmin(userRepository.countAllByRoles("ROLE_ADMIN"));
        numberOfUsers.setApplicants(userRepository.countAllByRoles("ROLE_APPLICANT"));
        numberOfUsers.setTotal(userRepository.count());
        return numberOfUsers;
    }
}
