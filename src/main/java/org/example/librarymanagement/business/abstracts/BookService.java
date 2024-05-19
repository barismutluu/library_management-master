package org.example.librarymanagement.business.abstracts;

import org.example.librarymanagement.entities.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Book save(Book book);
    Book get(Long id);
    Page<Book> cursor(int page, int pageSize);
    Book update(Book book);
    boolean delete(Long id);

}
