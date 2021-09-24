package com.example.data.postgres.migration.service;

import java.io.File;
import java.util.List;
import java.util.Iterator;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import com.univocity.parsers.csv.CsvParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.univocity.parsers.csv.CsvParserSettings;
import com.example.data.postgres.migration.model.TempMigrate;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.data.postgres.migration.repository.MigrateRepository;

@Service
public class DataProcessor {

    @Autowired MigrateRepository repository;
    @Value("${csv.file.path}") private String filePath;

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public void readAndProcess() throws Exception {

        LocalDateTime start = LocalDateTime.now();
        CsvParserSettings settings = new CsvParserSettings();
        CsvParser parser = new CsvParser(settings);
        Iterator<String[]> it = parser.iterate(new File(filePath), StandardCharsets.UTF_8).iterator();


        int batchSize = 0;
        List<TempMigrate> list = new ArrayList<>();

        while (it.hasNext()) {

            String[] row = it.next();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(row[1], dateFormat);
            list.add(new TempMigrate(Long.parseLong(row[0]), localDate, row[2], row[3], row[4], Long.parseLong(row[5]),
                    Long.parseLong(row[6]), Long.parseLong(row[7])));
            batchSize++;
            if (batchSize == 20000) {
                repository.saveAll(list);
                list.clear();
                batchSize=0;
            }
        }

        if (batchSize > 0) {
            repository.saveAll(list);
            list.clear();
        }
        System.out.println(getTime(start,LocalDateTime.now()));

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
