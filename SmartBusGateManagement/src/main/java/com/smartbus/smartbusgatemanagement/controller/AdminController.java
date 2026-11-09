package com.smartbus.smartbusgatemanagement.controller;

import com.smartbus.smartbusgatemanagement.service.BusLogService;
import com.smartbus.smartbusgatemanagement.service.RegisteredBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private RegisteredBusService registeredBusService;

    @Autowired
    private BusLogService busLogService;

    @GetMapping("/admin")
    public String adminLogin() {
        return "admin-login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalBuses",
                registeredBusService.getTotalRegisteredBuses());

        model.addAttribute("currentInside",
                busLogService.getCurrentBusesInside());

        model.addAttribute("exitedBuses",
                busLogService.getExitedBuses());

        model.addAttribute("totalLogs",
                busLogService.getTotalLogs());

        return "dashboard";
    }

    @GetMapping("/inside")
    public String insideBuses(Model model) {

        model.addAttribute("pageTitle", "Buses Currently Inside");
        model.addAttribute("logs",
                busLogService.getInsideBuses());

        return "bus-logs";
    }

    @GetMapping("/exited")
    public String exitedBuses(Model model) {

        model.addAttribute("pageTitle", "Exited Buses");
        model.addAttribute("logs",
                busLogService.getExitedBusesList());

        return "bus-logs";
    }
}