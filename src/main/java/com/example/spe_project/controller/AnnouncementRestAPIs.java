package com.example.spe_project.controller;

import com.example.spe_project.model.Announcement;
import com.example.spe_project.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementRestAPIs {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementRestAPIs(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/")
    public List<Announcement> getAnnouncement(){
        return announcementService.getAnnouncements();
    }

    @PostMapping
    public void addNewAnnouncement(@RequestBody Announcement announcement){
        announcementService.addAnnouncement(announcement);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAnnouncement(@PathVariable("id") Long id){
        announcementService.deleteAnnouncement(id);
    }

    @PutMapping(path = "{id}")
    public void updateAnnouncement(@PathVariable("id") Long id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description){
        announcementService.updateAnnouncement(id, title, description);
    }

}
