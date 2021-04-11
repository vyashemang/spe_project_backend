package com.example.spe_project.repository;

import com.example.spe_project.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Optional<Announcement> findById(Announcement announcement);

    @Query("SELECT a FROM Announcement a WHERE a.title=?1 AND a.description=?2")
    Announcement findAnnouncementByTitleAndDescription(String title, String description);
}
