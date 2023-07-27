package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class Book {
    private Long id;
    private String bookTitle;
    private String author; ;
    private int year ;
    private List<Author> authors;
}
