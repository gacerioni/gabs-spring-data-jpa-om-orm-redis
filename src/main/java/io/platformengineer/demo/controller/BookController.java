package io.platformengineer.demo.controller;

import io.platformengineer.demo.model.Book;
import io.platformengineer.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students/{studentId}/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@PathVariable Long studentId, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(studentId, book), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooksByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(bookService.findAllBooksByStudentId(studentId));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookByStudent(@PathVariable Long studentId, @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.findBookByIdAndStudentId(bookId, studentId));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long studentId, @PathVariable Long bookId, @RequestBody Book bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(studentId, bookId, bookDetails));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long studentId, @PathVariable Long bookId) {
        bookService.deleteBook(studentId, bookId);
        return ResponseEntity.noContent().build();
    }
}
