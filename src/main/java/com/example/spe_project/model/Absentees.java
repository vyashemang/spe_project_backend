package com.example.spe_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "absentees")
public class Absentees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "Asia/Kolkata")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;

    @NotBlank
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "Asia/Kolkata")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date endDate;

    public Absentees(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Absentees() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
