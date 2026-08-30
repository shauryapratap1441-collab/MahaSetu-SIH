package com.mahasetu.mahasetucore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CitizenController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/citizen-profile")
    public ResponseEntity<Map<String, Object>> getUnifiedProfile(@RequestParam String id) {

        String consentToken = "dummy-token-123"; // Temporary - will come from real consent flow later

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Consent-Token", consentToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        Map<String, Object> unifiedProfile = new HashMap<>();

        try {
            // 1. Call revenue-service
            ResponseEntity<Map> revenueResponse = restTemplate.exchange(
                    "http://localhost:8081/citizen?aadhar_no=" + id,
                    HttpMethod.GET, requestEntity, Map.class);
            Map<String, String> revenueData = revenueResponse.getBody();

            // 2. Call welfare-service
            ResponseEntity<Map> welfareResponse = restTemplate.exchange(
                    "http://localhost:8082/welfare-record?citizen_id=" + id,
                    HttpMethod.GET, requestEntity, Map.class);
            Map<String, String> welfareData = welfareResponse.getBody();

            // 3. Call cooperation-service
            ResponseEntity<Map> coopResponse = restTemplate.exchange(
                    "http://localhost:8083/coop-record?uid=" + id,
                    HttpMethod.GET, requestEntity, Map.class);
            Map<String, String> coopData = coopResponse.getBody();

            // 4. THE ADAPTER LOGIC: translate mismatched field names into one common format
            unifiedProfile.put("citizen_name", revenueData.get("name"));
            unifiedProfile.put("citizen_id", id);
            unifiedProfile.put("district", revenueData.get("district"));
            unifiedProfile.put("land_record_id", revenueData.get("land_record_id"));
            unifiedProfile.put("welfare_scheme", welfareData.get("scheme_name"));
            unifiedProfile.put("welfare_scheme_id", welfareData.get("scheme_id"));
            unifiedProfile.put("cooperative_society", coopData.get("society_name"));
            unifiedProfile.put("cooperative_reg_no", coopData.get("society_reg_no"));

            return new ResponseEntity<>(unifiedProfile, HttpStatus.OK);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to fetch unified profile: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}