package com.example.Bookinfo.management;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@SpringBootTest(classes=BookInfoManagementApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerIT {
  @Autowired
private TestRestTemplate testRestTemplate;

  @Test
    @Order(1)
    @DisplayName("Add new book - book not exists - success")
    void addNewBook_bookNotExists_success(){
      //Given
      String url="/api/v1/books";
      Book book= new Book();
      book.setId(28L);
      book.setBookTitle("React");
      book.setAuthor("Joe");
      book.setYear(1994);
      book.setCost(300.75);
      //Then
     HttpEntity<Book> request= new HttpEntity<>(book);
    ResponseEntity<Book> response= testRestTemplate.postForEntity(url,request,Book.class);
    log.info("Created-{}", response);
    Book created= response.getBody();
      //Verify
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }
@Test
  @Order(2)
  @DisplayName("Get book info- Book exists- success")
  void getBookInfo_bookExists_success(){

    //Given
    String url="/api/v1/books/22";

    //Then
  ResponseEntity<Book> response= testRestTemplate.getForEntity(url, Book.class);
  Book book= response.getBody();
  log.info("Got-{}",book);

  //Verify
  Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  @Order(3)
  @DisplayName("Get all books info - books exists-success")
  void getAllBooksInfo_booksExists_success(){

    //Given
    String url="/api/v1/books";

    //Then
    HttpEntity entity= new HttpEntity(null);
    ResponseEntity<List<Book>> response= testRestTemplate.exchange(url, HttpMethod.GET, entity,
            new ParameterizedTypeReference<List<Book>>() {});

    //Verify
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    List<Book> books = response.getBody();
    for(Book book : books) {
      log.info("{}", book);
    }
  }

  @Test
  @Order(4)
  @DisplayName("Delete book info - book exists - success")
  void deleteBookInfo_bookExists_success() {
    //Given
    String url= "/api/v1/books/28";

    //Then
    HttpEntity httpEntity = new HttpEntity<>(null);
    ResponseEntity<Void> response = testRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);

    // Verify
    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}

