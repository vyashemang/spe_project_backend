package com.example.spe_project.controller;

import com.example.spe_project.model.Announcement;
import com.example.spe_project.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementRestAPIs {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementRestAPIs(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }


    @GetMapping("/all")
    public List<Announcement> getAnnouncement(){
        System.out.printf("GOT REQ");
        return announcementService.getAnnouncements();
    }

    @PostMapping("/add")
    public void addNewAnnouncement(@RequestBody Announcement announcement){
        announcementService.addAnnouncement(announcement);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAnnouncement(@PathVariable("id") Long id){
        announcementService.deleteAnnouncement(id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateAnnouncement(@PathVariable("id") Long id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description){
        announcementService.updateAnnouncement(id, title, description);
    }

}
