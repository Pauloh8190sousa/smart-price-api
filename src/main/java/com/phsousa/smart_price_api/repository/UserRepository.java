package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
    SELECT DISTINCT u FROM User u
    JOIN FETCH u.roles r
    JOIN FETCH r.permissions
    WHERE u.email = :email
""")
    Optional<User> findByEmailWithRolesAndPermissions(String email);

}