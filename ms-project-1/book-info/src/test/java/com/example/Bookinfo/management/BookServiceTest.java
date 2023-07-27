package com.example.Bookinfo.management;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("GetBookInfo test case - book exists - success case")
    void getBookInfo_bookExists_success() {
        // Given
        Long bookId = 21L;
        Book book = new Book();
        book.setId(121L);
        book.setBookTitle("Angular");
        book.setAuthor("PMC");
        book.setYear(1996);
        book.setCost(2000.00);

        // Mock
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Then
        Book got = bookService.get(bookId);
        log.debug("{}", got);

        // Verify
        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verifyNoMoreInteractions(bookRepository);
    }

    @Test
    @DisplayName("GetBookInfo test case- book not exists- failure case")
    void getBookInfo_studentNotExists_failure(){
        //Given
        Long bookID=2222L;

        //Mock
        Mockito.when(bookRepository.findById(bookID)).thenReturn(Optional.ofNullable(null));

       //Then
       Book got= bookService.get(bookID);
       log.debug("{}",got);

        //Verify
        Assertions.assertNull(got);
        Mockito.verify(bookRepository).findById(bookID);
        Mockito.verifyNoMoreInteractions(bookRepository);
    }

@Test
    @DisplayName("DeleteBookInfo test case- book exists-success case")
    void deleteBookInfo_bookExists_success(){
        //Given
    Long bookId=22L;
    Book book = new Book();
    book.setId(121L);
    book.setBookTitle("Angular");
    book.setAuthor("PMC");
    book.setYear(1996);
    book.setCost(2000.00);
    //Mock
    Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
    //Then
    bookService.delete(bookId);

    //Verify
        Mockito.verify(bookRepository).findById(bookId);
       Mockito.verify(bookRepository).delete(book);
        Mockito.verifyNoMoreInteractions(bookRepository);
    }

    @Test
    @DisplayName("DeleteBookInfo test case -book not exists-failure case")
    void deleteBookInfo_bookNotExists_failure(){
        //Given
        Long bookId=101L;
        //Mock
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(null));
        //Then
        bookService.delete(bookId);
        //Verify
        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verifyNoMoreInteractions(bookRepository);
    }
}
