package com.example.spe_project.controller;


import com.example.spe_project.model.Absentees;
import com.example.spe_project.service.AbsenteesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/absentees")
public class AbsenteesRestAPIs {

    private final AbsenteesService absenteesService;

    @Autowired
    public AbsenteesRestAPIs(AbsenteesService absenteesService) {
        this.absenteesService = absenteesService;
    }

    @GetMapping("/get")
    public int getCount(){
        return absenteesService.getCount(new Date());
    }

    @PostMapping("/update")
    public void updateCount(@RequestBody Absentees absentees){
        absenteesService.updateCount(absentees);
    }

}
