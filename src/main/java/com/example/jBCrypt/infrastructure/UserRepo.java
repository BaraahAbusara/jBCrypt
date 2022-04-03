package com.example.jBCrypt.infrastructure;

import com.example.jBCrypt.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<MyUser, Long> {
     MyUser findByUsername(String username);
}
