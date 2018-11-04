package io.craigmiller160.springbootstarter.course.jpa;

import io.craigmiller160.springbootstarter.course.Course;
import io.craigmiller160.springbootstarter.course.CourseService;
import io.craigmiller160.springbootstarter.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
public class JpaCourseService implements CourseService {

    private final JpaCourseRepository courseRepository;

    @Autowired
    public JpaCourseService(JpaCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    @Override
    public Optional<List<Course>> getAllCourses(String topicId) {
        List<Course> courses = courseRepository.findByTopicId(topicId);

        if (courses.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(courses);
    }

    @Transactional
    @Override
    public Optional<Course> getCourse(String topicId, String courseId) {
        return courseRepository.findByTopicIdAndId(topicId, courseId);
    }

    @Transactional
    @Override
    public Optional<Course> addCourse(String topicId, Course course) {
        course.setTopic(new Topic(topicId, "", ""));
        course = courseRepository.save(course); //TODO this creates and updates together... wtf...
        return Optional.of(course);
    }

    @Transactional
    @Override
    public Optional<Course> updateCourse(String topicId, String courseId, Course course) {
        course.setTopic(new Topic(topicId, "", ""));
        course.setId(courseId);
        course = courseRepository.save(course);
        return Optional.of(course); //TODO this doesn't distinguish between something new or something updated
    }

    @Transactional
    @Override
    public Optional<Course> deleteCourse(String topicId, String courseId) {
        Optional<Course> course = courseRepository.findByTopicIdAndId(topicId, courseId);
        courseRepository.deleteByTopicIdAndId(topicId, courseId); //TODO don't like how this doesn't return anything...
        return course;
    }

}
