package com.smartbus.smartbusgatemanagement.repository;

import com.smartbus.smartbusgatemanagement.entity.RegisteredBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredBusRepository extends JpaRepository<RegisteredBus, Long> {

    RegisteredBus findByBusNumber(String busNumber);

}