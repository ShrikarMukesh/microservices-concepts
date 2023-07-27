package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@FeignClient("http://book-info-service")
public interface BookInfoClient {

    @GetMapping("/api/v1/books/{id}")
    Book getBook(@PathVariable String id);
}
