package com.mahasetu.mahasetucore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConsentLedger {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");

    public static class ConsentRecord {
        public String citizenId;
        public String departmentsAccessed;
        public String timestamp;

        public ConsentRecord(String citizenId, String departmentsAccessed) {
            this.citizenId = citizenId;
            this.departmentsAccessed = departmentsAccessed;
            this.timestamp = LocalDateTime.now().format(FORMATTER);
        }
    }

    private static final List<ConsentRecord> records = new ArrayList<>();

    public static void logAccess(String citizenId, String departmentsAccessed) {
        records.add(new ConsentRecord(citizenId, departmentsAccessed));
    }

    public static List<ConsentRecord> getAllRecords() {
        return records;
    }
}