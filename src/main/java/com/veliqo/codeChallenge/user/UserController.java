package com.veliqo.codeChallenge.user;

import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.exceptions.UpdateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/veliqo/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserDTOConverter converter;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.saveUser(converter.toEntity(user)).orElseThrow(()->
                new RecordExistException("Failed to create customer"));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        log.debug("finding user with email {}", email);
        UserDTO user = userService.findUserByEmail(email).orElseThrow(()->
                new RecordNotFoundException(String.format("User not found")));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> user = userService.findAllUsers();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{email}")
    public ResponseEntity<UserDTO> updateStatus(@PathVariable String email) {
        log.debug("updating user {}",email);
        UserDTO user = userService.updateStatus(email).orElseThrow(() ->
                new RuntimeException("Failed to update user"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{email}/{password}")
    public UpdateMessage updatePassword(@PathVariable String email, @PathVariable String password) {
        UpdateMessage updateMessage =  new UpdateMessage();
        int isUpdated = userService.updatePassword(password,email);
        if(isUpdated==1){
            updateMessage.setMessage(String.format("Password for user %s has been updated successfully.",email));
            updateMessage.setStatusCode(HttpStatus.OK.value());
            return  updateMessage;
        }
        throw new RuntimeException("Failed to update password");
    }

}
