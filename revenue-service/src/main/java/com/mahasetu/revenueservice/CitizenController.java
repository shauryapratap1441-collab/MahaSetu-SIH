package com.mahasetu.revenueservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CitizenController {

    @GetMapping("/citizen")
    public Map<String, String> getCitizen() {
        Map<String, String> citizen = new HashMap<>();
        citizen.put("aadhar_no", "1234-5678-9012");
        citizen.put("name", "Ramesh Kumar");
        citizen.put("land_record_id", "REV-MH-00123");
        citizen.put("district", "Pune");
        return citizen;
    }
}