package com.veliqo.codeChallenge.user;

import com.veliqo.codeChallenge.authentication.AuthRequest;
import com.veliqo.codeChallenge.authentication.AuthResponse;
import com.veliqo.codeChallenge.config.JwtService;
import com.veliqo.codeChallenge.exceptions.RecordExistException;
import com.veliqo.codeChallenge.exceptions.RecordNotFoundException;
import com.veliqo.codeChallenge.exceptions.UpdateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Author Richard K Chifamba on 9/24/2023
 **/
@Slf4j
@RestController
@RequestMapping("/api/v1/veliqo/user")
public class UserController {

    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @Autowired
    private UserDTOConverter converter;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.saveUser(converter.toEntity(user)).orElseThrow(()->
                new RecordExistException("Failed to create customer"));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        log.debug("finding user with email {}", email);

        UserDTO user = userService.findUserByUsername(email).orElseThrow(()->
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

    @PutMapping("/updatePassword/{email}/{currentPassword}/{newPassword}")
    public UpdateMessage updatePassword(@PathVariable String email, @PathVariable String currentPassword, @PathVariable String newPassword) {
        UpdateMessage updateMessage =  new UpdateMessage();
        int isUpdated = userService.updatePassword(email,currentPassword,newPassword);
        if(isUpdated==1){
            updateMessage.setMessage(String.format("Password for user %s has been updated successfully.",email));
            updateMessage.setStatusCode(HttpStatus.OK.value());
            return  updateMessage;
        }
        throw new RuntimeException("Failed to update password");
    }

    @PutMapping("/updateName/{email}/{name}")
    public ResponseEntity<UserDTO> updateName(@PathVariable String email, @PathVariable String name) {
        UserDTO user = userService.updateName(name,email).orElseThrow(() ->
                new RuntimeException("Failed to update user"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('USER_ADMIN')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDTO user = userService.findUserByUsername(authRequest.getUsername()).orElseThrow(()->
                    new RecordNotFoundException(String.format("User not found")));

             return  jwtService.generateToken(user);

        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/numberOfUsers")
    public ResponseEntity<NumberOfUsers> getTotalUsers() {
        return ResponseEntity.ok(userService.getTotalUsers());
    }

}
