package com.example.data.postgres.migration;

import com.example.data.postgres.migration.service.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DataPostgresMigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataPostgresMigrationApplication.class, args);
	}

	@Autowired DataProcessor dataProcessor;
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws Exception {
		dataProcessor.readAndProcess();
	}
}
