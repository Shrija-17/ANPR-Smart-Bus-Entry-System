package com.smartbus.smartbusgatemanagement.service;

import com.smartbus.smartbusgatemanagement.entity.Admin;
import com.smartbus.smartbusgatemanagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}