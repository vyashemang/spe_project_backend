package com.example.spe_project.service;

import com.example.spe_project.model.Announcement;
import com.example.spe_project.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public List<Announcement> getAnnouncements(){
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncement(Long id){
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Announcement with id: " + id +" does not exists!!"));
        return announcement;
    }

    public void addAnnouncement(Announcement announcement){
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Long id){
        if(!announcementRepository.existsById(id)) {
            throw new IllegalStateException("Announcement with id: " + id + " does not exists!!");
        }
        announcementRepository.deleteById(id);
    }

    @Transactional
    public void updateAnnouncement(Long id, String title, String description){
        Announcement announcement = announcementRepository.findById(id).orElseThrow( ()
                -> new IllegalStateException("Announcement with id: " + id +" does not exists!!"));

        if(title != null && title.length() > 0 && !Objects.equals(announcement.getTitle(), title)){
            announcement.setTitle(title);
        }

        if(description != null && description.length() > 0 && !Objects.equals(announcement.getDescription(), description)){
            announcement.setDescription(description);
        }
    }
}
