package io.platformengineer.demo.repository;

import io.platformengineer.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find all books belonging to a specific student
    List<Book> findAllByStudentId(Long studentId);

    // Find a specific book belonging to a specific student
    Optional<Book> findByIdAndStudentId(Long id, Long studentId);
}
