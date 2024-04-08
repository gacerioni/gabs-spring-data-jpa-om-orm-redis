package io.platformengineer.demo.controller.redis;

import io.platformengineer.demo.model.redis.StudentRedis;
import io.platformengineer.demo.repository.redis.StudentRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student-redis")
public class StudentRedisController {
    @Autowired
    StudentRedisRepository repo;

    @GetMapping("all")
    Iterable<StudentRedis> all() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    Optional<StudentRedis> byId(@PathVariable String id) {
        return repo.findById(id);
    }

    @GetMapping("age_between")
    Iterable<StudentRedis> byAgeBetween( //
                                         @RequestParam("min") int min, //
                                         @RequestParam("max") int max) {
        return repo.findByAgeBetween(min, max);
    }
}
