package io.platformengineer.demo.service;

import io.platformengineer.demo.model.Book;
import java.util.List;

public interface BookService {
    Book addBook(Long studentId, Book book);
    List<Book> findAllBooksByStudentId(Long studentId);
    Book findBookByIdAndStudentId(Long bookId, Long studentId);
    Book updateBook(Long studentId, Long bookId, Book bookDetails);
    void deleteBook(Long studentId, Long bookId);
}
