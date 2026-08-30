package com.mahasetu.coorporationservice;

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

    private static final Map<String, Map<String, String>> memberDatabase = new HashMap<>();

    static {
        Map<String, String> member1 = new HashMap<>();
        member1.put("uid", "1234-5678-9012");
        member1.put("member_name", "Ramesh Kumar");
        member1.put("society_reg_no", "COOP-MH-3321");
        member1.put("society_name", "Pune Farmers Cooperative");
        memberDatabase.put("1234-5678-9012", member1);

        Map<String, String> member2 = new HashMap<>();
        member2.put("uid", "9876-5432-1098");
        member2.put("member_name", "Anjali Deshmukh");
        member2.put("society_reg_no", "COOP-MH-9087");
        member2.put("society_name", "Nagpur Handicrafts Cooperative");
        memberDatabase.put("9876-5432-1098", member2);
    }

    @GetMapping("/coop-record")
    public ResponseEntity<Map<String, String>> getCoopRecord(
            @RequestParam(required = false) String uid,
            @RequestHeader(value = "X-Consent-Token", required = false) String consentToken) {

        if (consentToken == null || consentToken.isBlank()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Missing consent token. Access denied.");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        String lookupId = (uid != null) ? uid : "1234-5678-9012";
        Map<String, String> record = memberDatabase.get(lookupId);

        if (record == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Cooperative record not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(record, HttpStatus.OK);
    }
}