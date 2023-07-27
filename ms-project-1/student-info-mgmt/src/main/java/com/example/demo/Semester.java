package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Semester {

    @Id
    private Long id;
    private LocalDate startingDate;
    private LocalDate endDate;
    @OneToMany
    private List<Subject> subjects;
}
