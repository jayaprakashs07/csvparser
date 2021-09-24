package com.example.data.postgres.migration.repository;

import com.example.data.postgres.migration.model.TempMigrate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MigrateRepository extends JpaRepository<TempMigrate, Integer> {

}
