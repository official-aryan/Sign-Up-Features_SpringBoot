package com.Signup_Features.Signup_Features.Repository;

import com.Signup_Features.Signup_Features.Entity.User_detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Repository extends JpaRepository<User_detail,Integer> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
