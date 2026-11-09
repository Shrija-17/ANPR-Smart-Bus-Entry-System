package com.smartbus.smartbusgatemanagement.controller;

import com.smartbus.smartbusgatemanagement.entity.BusLog;
import com.smartbus.smartbusgatemanagement.entity.RegisteredBus;
import com.smartbus.smartbusgatemanagement.service.BusLogService;
import com.smartbus.smartbusgatemanagement.service.PythonService;
import com.smartbus.smartbusgatemanagement.service.RegisteredBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BusController {

    @Autowired
    private RegisteredBusService registeredBusService;

    @Autowired
    private BusLogService busLogService;

    @Autowired
    private PythonService pythonService;

    @GetMapping("/register-bus")
    public String showRegisterForm(Model model) {
        model.addAttribute("bus", new RegisteredBus());
        return "register-bus";
    }

    @PostMapping("/save-bus")
    public String saveBus(@ModelAttribute RegisteredBus bus) {
        registeredBusService.saveBus(bus);
        return "redirect:/buses";
    }

    @GetMapping("/buses")
    public String busList(Model model) {
        model.addAttribute("buses", registeredBusService.getAllBuses());
        return "bus-list";
    }

    @GetMapping("/logs")
    public String busLogs(
            @RequestParam(required = false) String busNumber,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String destination,
            Model model) {

        model.addAttribute("pageTitle", "All Bus Logs");

        if (busNumber != null && !busNumber.isBlank()) {

            model.addAttribute("logs",
                    busLogService.searchByBusNumber(busNumber));

        } else if (date != null && !date.isBlank()) {

            model.addAttribute("logs",
                    busLogService.searchByDate(date));

        } else if (destination != null && !destination.isBlank()) {

            model.addAttribute("logs",
                    busLogService.searchByDestination(destination));

        } else {

            model.addAttribute("logs",
                    busLogService.getAllLogs());

        }

        model.addAttribute("busNumber", busNumber);
        model.addAttribute("date", date);
        model.addAttribute("destination", destination);

        return "bus-logs";
    }

    @GetMapping("/camera")
    public String camera(Model model) {

        // Detect number plate using Python
        String plateNumber = pythonService.detectPlate();

        model.addAttribute("plateNumber", plateNumber);

        // Search registered bus
        RegisteredBus bus = registeredBusService.getBusByNumber(plateNumber);

        if (bus != null) {

            model.addAttribute("bus", bus);

            // Check if the bus is already inside
            BusLog openLog = busLogService.getOpenLog(bus.getBusNumber()).orElse(null);

            if (openLog == null) {

                // ENTRY
                BusLog log = new BusLog();

                log.setBusNumber(bus.getBusNumber());
                log.setDestination(bus.getDestination());
                log.setDate(LocalDate.now().toString());

                log.setEntryTime(
                        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                );

                log.setExitTime(null);

                busLogService.saveLog(log);

                model.addAttribute("status", "ENTRY ALLOWED");

            } else {

                // EXIT
                openLog.setExitTime(
                        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                );

                busLogService.saveLog(openLog);

                model.addAttribute("status", "EXIT RECORDED");
            }

        } else {

            model.addAttribute("status", "ENTRY DENIED");

        }

        return "camera";
    }
}