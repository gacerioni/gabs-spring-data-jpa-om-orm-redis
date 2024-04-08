package io.platformengineer.demo.service.impl;

import io.platformengineer.demo.model.Book;
import io.platformengineer.demo.model.Student;
import io.platformengineer.demo.repository.BookRepository;
import io.platformengineer.demo.repository.StudentRepository;
import io.platformengineer.demo.service.BookService;
import io.platformengineer.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Book addBook(Long studentId, Book book) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        book.setStudent(student);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBooksByStudentId(Long studentId) {
        // Implementation here assumes a method in BookRepository: findAllByStudentId
        return bookRepository.findAllByStudentId(studentId);
    }

    @Override
    public Book findBookByIdAndStudentId(Long bookId, Long studentId) {
        // This method may need a custom query in BookRepository to implement
        return bookRepository.findByIdAndStudentId(bookId, studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId + " for studentId: " + studentId));
    }

    @Override
    public Book updateBook(Long studentId, Long bookId, Book bookDetails) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        Book book = findBookByIdAndStudentId(bookId, studentId);
        book.setBookName(bookDetails.getBookName());
        // Update other fields as necessary
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long studentId, Long bookId) {
        Book book = findBookByIdAndStudentId(bookId, studentId);
        bookRepository.delete(book);
    }
}
