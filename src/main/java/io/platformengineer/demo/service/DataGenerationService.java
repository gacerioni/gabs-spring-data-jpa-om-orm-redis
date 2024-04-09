package io.platformengineer.demo.service;

import com.github.javafaker.Faker;
import io.platformengineer.demo.model.Book;
import io.platformengineer.demo.model.Course;
import io.platformengineer.demo.model.Student;
import io.platformengineer.demo.model.StudentIdCard;
import io.platformengineer.demo.repository.BookRepository;
import io.platformengineer.demo.repository.CourseRepository;
import io.platformengineer.demo.repository.StudentIdCardRepository;
import io.platformengineer.demo.repository.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DataGenerationService {

    private final Faker faker = new Faker();
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentIdCardRepository studentIdCardRepository;
    private final BookRepository bookRepository;

    public DataGenerationService(CourseRepository courseRepository, StudentRepository studentRepository,
                                 StudentIdCardRepository studentIdCardRepository, BookRepository bookRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentIdCardRepository = studentIdCardRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        // Register a few Courses
        registerCourses();
    }

    private void registerCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Mathematics", "Science"));
        courses.add(new Course("Literature", "Arts"));
        courses.add(new Course("Physics", "Science"));
        // Add more courses as needed
        courseRepository.saveAll(courses);
    }

    @Scheduled(fixedDelay = 3000)
    public void registerStudentAndRelatedData() {
        for(int j = 0; j < 3; j++) {
            Student student = registerStudent();
            registerBooksForStudent(student);
        }
    }

    private Student registerStudent() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        int age = faker.number().numberBetween(18, 30);
        Student student = new Student(firstName, lastName, email, age);
        return studentRepository.save(student);
    }

    private void registerStudentIdCard(Student student) {
        StudentIdCard studentIdCard = new StudentIdCard(faker.idNumber().valid(), student);
        studentIdCardRepository.save(studentIdCard);
    }

    private void registerBooksForStudent(Student student) {
        List<Book> books = new ArrayList<>();
        // Create the books
        Book book1 = new Book("The Great Gatsby", LocalDateTime.now().minusYears(2));
        Book book2 = new Book("Moby Dick", LocalDateTime.now().minusYears(4));
        // Set the student for each book
        book1.setStudent(student);
        book2.setStudent(student);
        // Add books to the list
        books.add(book1);
        books.add(book2);
        // Save all books
        bookRepository.saveAll(books);
    }

}
