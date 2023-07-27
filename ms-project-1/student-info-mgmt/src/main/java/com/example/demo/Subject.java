package com.example.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Subject {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;
        private String subName;
        private String author;

//        @PrePersist
//        private void ensureId(){
//                this.setId(UUID.randomUUID().toString());
//        }
}

