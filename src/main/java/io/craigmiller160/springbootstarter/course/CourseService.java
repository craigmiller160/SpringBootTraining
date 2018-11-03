package io.craigmiller160.springbootstarter.course;

import io.craigmiller160.springbootstarter.topic.Topic;
import io.craigmiller160.springbootstarter.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {

    //TODO add spring transaction handling to these methods

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<List<Course>> getAllCourses() {
        List<Course> courses = StreamSupport.stream(courseRepository.findAll().spliterator(),  false)
                .collect(Collectors.toList());

        if (courses.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(courses);
    }

    public Optional<Course> getCourse(String id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> addCourse(Course course) {
        course = courseRepository.save(course); //TODO this creates and updates together... wtf...
        return Optional.of(course);
    }

    public Optional<Course> updateCourse(String id, Course course) {
        course = courseRepository.save(course);
        return Optional.of(course); //TODO this doesn't distinguish between something new or something updated
    }

    public Optional<Course> deleteCourse(String id) {
        Optional<Course> course = courseRepository.findById(id);
        courseRepository.deleteById(id); //TODO don't like how this doesn't return anything...
        return course;
    }

}
