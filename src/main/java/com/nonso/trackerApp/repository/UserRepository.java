package com.nonso.trackerApp.repository;

import com.nonso.trackerApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE email=?1 AND password=?2", nativeQuery = true)
    User findByEmailAAndPassword(String email, String password);
}
