package com.mahasetu.welfareservice;

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
        citizen1.put("citizen_id", "1234-5678-9012");
        citizen1.put("full_name", "Ramesh Kumar");
        citizen1.put("scheme_id", "WEL-MH-4521");
        citizen1.put("scheme_name", "Old Age Pension Scheme");
        citizenDatabase.put("1234-5678-9012", citizen1);

        Map<String, String> citizen2 = new HashMap<>();
        citizen2.put("citizen_id", "9876-5432-1098");
        citizen2.put("full_name", "Anjali Deshmukh");
        citizen2.put("scheme_id", "WEL-MH-7734");
        citizen2.put("scheme_name", "Widow Pension Scheme");
        citizenDatabase.put("9876-5432-1098", citizen2);
    }

    @GetMapping("/welfare-record")
    public ResponseEntity<Map<String, String>> getWelfareRecord(
            @RequestParam(required = false) String citizen_id,
            @RequestHeader(value = "X-Consent-Token", required = false) String consentToken) {

        if (consentToken == null || consentToken.isBlank()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Missing consent token. Access denied.");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        String lookupId = (citizen_id != null) ? citizen_id : "1234-5678-9012";
        Map<String, String> record = citizenDatabase.get(lookupId);

        if (record == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Welfare record not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(record, HttpStatus.OK);
    }
}