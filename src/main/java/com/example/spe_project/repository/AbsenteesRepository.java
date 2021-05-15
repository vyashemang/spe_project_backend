package com.example.spe_project.repository;

import com.example.spe_project.model.Absentees;
import com.example.spe_project.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AbsenteesRepository extends JpaRepository<Absentees, Long> {

    // Get count
    @Query("SELECT count(ab) FROM Absentees ab WHERE ab.startDate<=?1 AND ab.endDate >= ?1")
    int findByDate(Date date);



}
