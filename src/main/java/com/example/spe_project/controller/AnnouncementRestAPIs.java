package com.example.spe_project.controller;

import com.example.spe_project.model.Announcement;
import com.example.spe_project.service.AnnouncementService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementRestAPIs {

    private final AnnouncementService announcementService;

    private static final Logger logger = LoggerFactory.getLogger(AnnouncementRestAPIs.class);

    @Autowired
    public AnnouncementRestAPIs(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/all")
    public List<Announcement> getAnnouncement(){
        logger.info("get all announcement");
        return announcementService.getAnnouncements();
    }

    @GetMapping(path = "/get/{id}")
    public Announcement getAnnouncement(@PathVariable("id") Long id){
        Announcement announcement = null;
        try {
            announcement = announcementService.getAnnouncement(id);
            logger.info("get " + id + " announcement");
        }
        catch (IllegalStateException e){
            logger.error("announcement " + id + " not found");
        }
        return announcement;
    }

    @PostMapping("/add")
    public void addNewAnnouncement(@RequestBody Announcement announcement){
        announcementService.addAnnouncement(announcement);
        logger.info("add announcement");
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAnnouncement(@PathVariable("id") Long id){
        try{
            announcementService.deleteAnnouncement(id);
            logger.info("delete " + id + " announcement");
        }
        catch(IllegalStateException e) {
            logger.error("announcement " + id + " not found");
        }
    }

    @GetMapping(path = "/update/{id}")
    public void updateAnnouncement(@PathVariable("id") Long id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String description){
        try{
            announcementService.updateAnnouncement(id, title, description);
            logger.info("update" + id +" announcement");
        }
        catch (IllegalStateException e) {
            logger.error("announcement " + id + " not found");
        }
    }
}