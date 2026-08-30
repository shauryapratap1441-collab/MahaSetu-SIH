package com.mahasetu.mahasetucore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsentLedger {

    // A single record of one consent-based data access event
    public static class ConsentRecord {
        public String citizenId;
        public String departmentsAccessed;
        public String timestamp;

        public ConsentRecord(String citizenId, String departmentsAccessed) {
            this.citizenId = citizenId;
            this.departmentsAccessed = departmentsAccessed;
            this.timestamp = LocalDateTime.now().toString();
        }
    }

    // Shared, in-memory list of every access event so far
    private static final List<ConsentRecord> records = new ArrayList<>();

    public static void logAccess(String citizenId, String departmentsAccessed) {
        records.add(new ConsentRecord(citizenId, departmentsAccessed));
    }

    public static List<ConsentRecord> getAllRecords() {
        return records;
    }
}