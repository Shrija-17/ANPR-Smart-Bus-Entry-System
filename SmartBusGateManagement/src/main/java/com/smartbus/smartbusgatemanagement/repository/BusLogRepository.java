package com.smartbus.smartbusgatemanagement.repository;

import com.smartbus.smartbusgatemanagement.entity.BusLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusLogRepository extends JpaRepository<BusLog, Long> {

    // Find latest open log for a bus
    Optional<BusLog> findTopByBusNumberAndExitTimeIsNullOrderByIdDesc(String busNumber);

    // Dashboard Statistics
    long countByExitTimeIsNull();

    long countByDate(String date);

    long countByDateAndExitTimeIsNotNull(String date);
    // Total Exited Buses
    long countByExitTimeIsNotNull();

    // Total Bus Logs
    long count();
    // Inside Buses
    List<BusLog> findAllByExitTimeIsNull();

    // Exited Buses
    List<BusLog> findAllByExitTimeIsNotNull();

    List<BusLog> findByExitTimeIsNull();

    // Search by Bus Number
    List<BusLog> findByBusNumberContainingIgnoreCase(String busNumber);
    // Search by Date
    List<BusLog> findByDate(String date);
    // Search by Destination
    List<BusLog> findByDestinationContainingIgnoreCase(String destination);
    // Total Exited Buses


}