package io.craigmiller160.springbootstarter.course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Optional<List<Course>> getAllCourses(String topicId);

    Optional<Course> getCourse(String topicId, String courseId);

    Optional<Course> addCourse(String topicId, Course course);

    Optional<Course> updateCourse(String topicId, String courseId, Course course);

    Optional<Course> deleteCourse(String topicId, String courseId);

}
