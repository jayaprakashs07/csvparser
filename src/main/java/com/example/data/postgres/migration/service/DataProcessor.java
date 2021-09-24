package com.example.data.postgres.migration.service;

import java.io.File;
import java.util.List;
import java.util.Iterator;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import com.univocity.parsers.csv.CsvParser;
import org.springframework.stereotype.Service;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Value;
import com.example.data.postgres.migration.model.TempMigrate;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.data.postgres.migration.repository.MigrateRepository;

@Service
@Log4j2
public class DataProcessor {

    @Autowired MigrateRepository repository;
    @Value("${csv.file.path}") private String filePath;

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public void readAndProcess() {

        LocalDateTime start = LocalDateTime.now();

        log.info("Execution of method readAndProcess() started.. ");
        log.info("Start time {} ", start);

        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        Iterator<String[]> it = parser.iterate(new File(filePath), StandardCharsets.UTF_8).iterator();


        int batchSize = 0;
        int count = 1;
        List<TempMigrate> list = new ArrayList<>();

        while (it.hasNext()) {

            String[] row = it.next();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(row[1], dateFormat);
            list.add(new TempMigrate(Long.parseLong(row[0]), localDate, row[2], row[3], row[4], Long.parseLong(row[5]),
                    Long.parseLong(row[6]), Long.parseLong(row[7])));
            batchSize++;
            if (batchSize == 20000) {
                try {
                    repository.saveAll(list);
                    list.clear();
                    batchSize=0;
                } catch (Exception e) {
                    log.info("Exception occurs {} ", e.getMessage());
                }
                log.info("Records inserted {} ", count);
            }
            count++;
        }

        if (batchSize > 0) {
            try {
                repository.saveAll(list);
                list.clear();
            } catch (Exception e) {
                log.info("Exception occurs {} ", e.getMessage());
            }
        }

        LocalDateTime end = LocalDateTime.now();
        log.info("End time {} ", end);
        log.info("Total amount of time to process the CSV file {} ", getTime(start,end));
    }

    private String getTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime today = LocalDateTime.of(endTime.getYear(),
                endTime.getMonthValue(), endTime.getDayOfMonth(), startTime.getHour(), startTime.getMinute(), startTime.getSecond());
        Duration duration = Duration.between(today, endTime);
        long seconds = duration.getSeconds();
        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);
        return "" + hours + " | minutes " + minutes + " | sec :" + secs;
    }

}
