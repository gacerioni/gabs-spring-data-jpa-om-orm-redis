package io.platformengineer.demo.service;

import io.platformengineer.demo.model.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id, Course courseDetails);
    void deleteCourse(Long id);
}
