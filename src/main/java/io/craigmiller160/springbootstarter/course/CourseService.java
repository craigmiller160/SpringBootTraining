package io.craigmiller160.springbootstarter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Optional<List<Course>> getAllCourses(String topicId) {
        List<Course> courses = courseRepository.findByTopicId(topicId);

        if (courses.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(courses);
    }

    @Transactional
    public Optional<Course> getCourse(String id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Optional<Course> addCourse(Course course) {
        course = courseRepository.save(course); //TODO this creates and updates together... wtf...
        return Optional.of(course);
    }

    @Transactional
    public Optional<Course> updateCourse(String id, Course course) {
        course = courseRepository.save(course);
        return Optional.of(course); //TODO this doesn't distinguish between something new or something updated
    }

    @Transactional
    public Optional<Course> deleteCourse(String id) {
        Optional<Course> course = courseRepository.findById(id);
        courseRepository.deleteById(id); //TODO don't like how this doesn't return anything...
        return course;
    }

}
