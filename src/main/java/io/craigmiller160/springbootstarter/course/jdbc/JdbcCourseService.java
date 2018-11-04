package io.craigmiller160.springbootstarter.course.jdbc;

import io.craigmiller160.springbootstarter.course.Course;
import io.craigmiller160.springbootstarter.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JdbcCourseService implements CourseService {

    private static final String SELECT_ALL_COURSES =
            "SELECT c.*, t.name AS topic_name, t.description AS topic_description " +
            "FROM course c " +
            "JOIN topic t ON c.topic_id = t.id " +
            "WHERE c.topic_id = ?";
    private static final String SELECT_ONE_COURSE =
            "SELECT c.*, t.name AS topic_name, t.description AS topic_description " +
            "FROM course c " +
            "JOIN topic t ON c.topic_id = t.id " +
            "WHERE c.topic_id = ? AND c.id = ?";
    private static final String INSERT_COURSE = "INSERT INTO course (id, name, description, topic_id) VALUES (?,?,?,?)";
    private static final String UPDATE_COURSE = "UPDATE course SET name = ?, description = ? WHERE topic_id = ? AND id = ?";
    private static final String DELETE_COURSE = "DELETE FROM course WHERE topic_id = ? AND id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCourseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Optional<List<Course>> getAllCourses(String topicId) {
        List<Course> courses = jdbcTemplate.query(SELECT_ALL_COURSES, new Object[]{topicId}, new CourseRowMapper());
        if (courses.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(courses);
    }

    @Transactional
    @Override
    public Optional<Course> getCourse(String topicId, String courseId) {
        List<Course> courses = jdbcTemplate.query(SELECT_ONE_COURSE, new Object[]{topicId, courseId}, new CourseRowMapper());
        if (courses.size() == 0) {
            return Optional.empty();
        }
        else if (courses.size() > 1) {
            throw new RuntimeException("Database error occurred. Too many courses with the same primary key were returned. Size: " + courses.size());
        }

        return Optional.of(courses.get(0));
    }

    @Transactional
    @Override
    public Optional<Course> addCourse(String topicId, Course course) {
        int result = jdbcTemplate.update(INSERT_COURSE, course.getId(), course.getName(), course.getDescription(), topicId);
        if (result == 0) {
            return Optional.empty();
        }

        return getCourse(topicId, course.getId());
    }

    @Transactional
    @Override
    public Optional<Course> updateCourse(String topicId, String courseId, Course course) {
        int result = jdbcTemplate.update(UPDATE_COURSE, course.getName(), course.getDescription(), topicId, courseId);
        if (result == 0) {
            return Optional.empty();
        }

        return getCourse(topicId, courseId);
    }

    @Transactional
    @Override
    public Optional<Course> deleteCourse(String topicId, String courseId) {
        Optional<Course> course = getCourse(topicId, courseId);
        int result = jdbcTemplate.update(DELETE_COURSE, topicId, courseId);
        if (result == 0) {
            return Optional.empty();
        }

        return course;
    }
}
