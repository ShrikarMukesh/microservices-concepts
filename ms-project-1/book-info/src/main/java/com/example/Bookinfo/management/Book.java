package com.example.Bookinfo.management;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String bookTitle;
    private String author; ;
    private int year ;
    private double cost ;
    @ManyToMany
    private List<Author> authors;
}
