package org.example.librarymanagement.business.abstracts;

import org.example.librarymanagement.entities.BookBorrowing;
import org.springframework.data.domain.Page;

public interface BookBorrowingService {

    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(Long id);
    Page<BookBorrowing> cursor(int page, int pageSize);
    BookBorrowing update(BookBorrowing bookBorrowing);
    boolean delete(Long id);
}
