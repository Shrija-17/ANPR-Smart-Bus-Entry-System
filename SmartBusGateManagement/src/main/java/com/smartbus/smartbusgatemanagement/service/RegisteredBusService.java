package com.smartbus.smartbusgatemanagement.service;

import com.smartbus.smartbusgatemanagement.entity.RegisteredBus;
import com.smartbus.smartbusgatemanagement.repository.RegisteredBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredBusService {

    @Autowired
    private RegisteredBusRepository repository;

    public RegisteredBus saveBus(RegisteredBus bus) {
        return repository.save(bus);
    }

    public List<RegisteredBus> getAllBuses() {
        return repository.findAll();
    }

    public RegisteredBus getBusByNumber(String busNumber) {

        if (busNumber == null) {
            return null;
        }

        // Remove spaces and convert OCR result to uppercase
        String detected = busNumber.replaceAll("\\s+", "").toUpperCase();

        for (RegisteredBus bus : repository.findAll()) {

            String dbNumber = bus.getBusNumber()
                    .replaceAll("\\s+", "")
                    .toUpperCase();

            if (dbNumber.equals(detected)) {
                return bus;
            }
        }

        return null;
    }

    public void deleteBus(Long id) {
        repository.deleteById(id);
    }

    // Dashboard Statistics
    public long getTotalRegisteredBuses() {
        return repository.count();
    }
}