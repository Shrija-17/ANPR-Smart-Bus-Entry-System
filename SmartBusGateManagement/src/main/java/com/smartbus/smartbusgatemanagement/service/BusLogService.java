package com.smartbus.smartbusgatemanagement.service;

import com.smartbus.smartbusgatemanagement.entity.BusLog;
import com.smartbus.smartbusgatemanagement.repository.BusLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BusLogService {

    @Autowired
    private BusLogRepository repository;

    public BusLog saveLog(BusLog log) {
        return repository.save(log);
    }

    public List<BusLog> getAllLogs() {
        return repository.findAll();
    }

    public Optional<BusLog> getOpenLog(String busNumber) {
        return repository.findTopByBusNumberAndExitTimeIsNullOrderByIdDesc(busNumber);
    }

    // Dashboard Statistics

    public long getCurrentBusesInside() {
        return repository.countByExitTimeIsNull();
    }

    // Total Exited Buses
    public long getExitedBuses() {
        return repository.countByExitTimeIsNotNull();
    }

    // Total Bus Logs
    public long getTotalLogs() {
        return repository.count();
    }

    public List<BusLog> getCurrentBuses() {
        return repository.findByExitTimeIsNull();
    }

    // Search by Bus Number

    public List<BusLog> searchByBusNumber(String busNumber) {
        return repository.findByBusNumberContainingIgnoreCase(busNumber);
    }
    // Search by Date
    public List<BusLog> searchByDate(String date) {
        return repository.findByDate(date);
    }
    // Search by Destination
    public List<BusLog> searchByDestination(String destination) {
        return repository.findByDestinationContainingIgnoreCase(destination);
    }
    // Get Inside Buses
    public List<BusLog> getInsideBuses() {
        return repository.findAllByExitTimeIsNull();
    }

    // Get Exited Buses
    public List<BusLog> getExitedBusesList() {
        return repository.findAllByExitTimeIsNotNull();
    }

}