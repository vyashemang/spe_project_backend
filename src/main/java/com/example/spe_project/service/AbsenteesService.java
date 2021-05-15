package com.example.spe_project.service;

import com.example.spe_project.model.Absentees;
import com.example.spe_project.repository.AbsenteesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AbsenteesService {

    private final AbsenteesRepository absenteesRepository;

    @Autowired
    public AbsenteesService(AbsenteesRepository absenteesRepository) {
        this.absenteesRepository = absenteesRepository;
    }

    public int getCount(Date date){
        int rows = absenteesRepository.findByDate(date);
        return 500 - rows;
    }

    public void updateCount(Absentees absentees){
        absenteesRepository.save(absentees);
    }


}
