package com.Signup_Features.Signup_Features.Controller;

import com.Signup_Features.Signup_Features.Entity.User_detail;
import com.Signup_Features.Signup_Features.Payload.User_detail_DTO;
import com.Signup_Features.Signup_Features.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class User_Controller {

    @Autowired
    private User_Repository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    // http://localhost:8080/api/user/signup
    public ResponseEntity<?> registerUser(@RequestBody User_detail_DTO user_detail_dto) {

        if (repo.existsByEmail(user_detail_dto.getEmail())) {

            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);

        }

        if (repo.existsByUsername(user_detail_dto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User_detail user = new User_detail();
        user.setId(user_detail_dto.getId());
        user.setUsername(user_detail_dto.getUsername());
        user.setEmail(user_detail_dto.getEmail());
        user.setPassword(passwordEncoder.encode(user_detail_dto.getPassword()));

        repo.save(user);

        return new ResponseEntity<>("The user registered successfully!!", HttpStatus.CREATED);
    }
}