package io.platformengineer.demo;

import com.github.javafaker.Faker;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import io.platformengineer.demo.model.*;
import io.platformengineer.demo.model.redis.StudentRedis;
import io.platformengineer.demo.repository.StudentIdCardRepository;
import io.platformengineer.demo.repository.StudentRepository;
import io.platformengineer.demo.repository.redis.StudentRedisRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.time.LocalDateTime;
import java.util.List;

@EnableRedisDocumentRepositories(basePackages = "io.platformengineer.demo.*")
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner loadTestData(StudentRedisRepository repo) {
        return args -> {
            repo.deleteAll();

            Faker faker = new Faker();

            // Generate and save 10 fake StudentRedis entities
            for (int i = 0; i < 5; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                int age = faker.number().numberBetween(18, 30); // Random age between 18 and 30
                String email = faker.internet().emailAddress();

                    StudentRedis studentRedis = StudentRedis.of(firstName, lastName, age, email);
                repo.save(studentRedis);
            }
        };
    }


    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            student.addBook(
                    new Book("Clean Code", LocalDateTime.now().minusDays(4)));

            student.addBook(
                    new Book("Think and Grow Rich", LocalDateTime.now()));

            student.addBook(
                    new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

            StudentIdCard studentIdCard = new StudentIdCard(
                    "123456789",
                    student);

            student.setStudentIdCard(studentIdCard);

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 1L),
                    student,
                    new Course("Computer Science", "IT"),
                    LocalDateTime.now()
            ));

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 2L),
                    student,
                    new Course("Amigoscode Spring Data JPA", "IT"),
                    LocalDateTime.now().minusDays(18)
            ));

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 2L),
                    student,
                    new Course("Amigoscode Spring Data JPA", "IT"),
                    LocalDateTime.now().minusDays(18)
            ));



            studentRepository.save(student);

            studentRepository.findById(1L)
                    .ifPresent(s -> {
                        System.out.println("fetch book lazy...");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> {
                            System.out.println(
                                    s.getFirstName() + " borrowed " + book.getBookName());
                        });
                    });

        };
    }
}