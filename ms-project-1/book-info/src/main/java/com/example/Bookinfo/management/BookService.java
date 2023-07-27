    package com.example.Bookinfo.management;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class BookService {
        private final BookRepository bookRepository;

        public Book get(Long id) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            log.debug("Get book:{}", id);
            if (optionalBook.isPresent() )
                return optionalBook.get();
            return null;
        }

        public Book create(Book book) {
            Book saved = bookRepository.save(book);
            log.debug("Added:{}", saved);
            return saved;
        }

        public Book update(Long id, Book book) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                bookRepository.save(book);
                log.debug("{} updated", id);
            } else {
                log.debug("Book with id:{} is not found.", id);
            }
            return book;
        }

        public void delete(Long id) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                bookRepository.delete(optionalBook.get()); ;
                log.debug("{} deleted.", id);
            } else {
                log.debug("Book with id:{} is not found.", id);
            }
        }

        public List<Book> getAll() {
            Iterable<Book> it = bookRepository.findAll();
            List<Book> books = new ArrayList<>();
            for (Book book : it) {
                books.add(book);
            }
            return books;
        }
    }