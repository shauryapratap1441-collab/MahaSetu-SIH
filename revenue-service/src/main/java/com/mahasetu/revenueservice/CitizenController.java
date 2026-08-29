package com.mahasetu.revenueservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CitizenController {

    private static final Map<String, Map<String, String>> citizenDatabase = new HashMap<>();

    static {
        Map<String, String> citizen1 = new HashMap<>();
        citizen1.put("aadhar_no", "1234-5678-9012");
        citizen1.put("name", "Ramesh Kumar");
        citizen1.put("land_record_id", "REV-MH-00123");
        citizen1.put("district", "Pune");
        citizenDatabase.put("1234-5678-9012", citizen1);

        Map<String, String> citizen2 = new HashMap<>();
        citizen2.put("aadhar_no", "9876-5432-1098");
        citizen2.put("name", "Anjali Deshmukh");
        citizen2.put("land_record_id", "REV-MH-00789");
        citizen2.put("district", "Nagpur");
        citizenDatabase.put("9876-5432-1098", citizen2);
    }

    @GetMapping("/citizen")
    public ResponseEntity<Map<String, String>> getCitizen(
            @RequestParam(required = false) String aadhar_no,
            @RequestHeader(value = "X-Consent-Token", required = false) String consentToken) {

        if (consentToken == null || consentToken.isBlank()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Missing consent token. Access denied.");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        String lookupId = (aadhar_no != null) ? aadhar_no : "1234-5678-9012";
        Map<String, String> citizen = citizenDatabase.get(lookupId);

        if (citizen == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Citizen not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(citizen, HttpStatus.OK);
    }
}