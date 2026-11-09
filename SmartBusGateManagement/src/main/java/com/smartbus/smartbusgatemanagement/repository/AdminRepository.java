package com.smartbus.smartbusgatemanagement.repository;

import com.smartbus.smartbusgatemanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

}