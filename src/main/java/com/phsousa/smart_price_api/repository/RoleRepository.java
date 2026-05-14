package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Query("""
    SELECT r
    FROM Role r
    LEFT JOIN FETCH r.permissions
    WHERE r.name = :name
""")
    Optional<Role> findByNameWithPermissions(String name);
}
