package io.craigmiller160.springbootstarter.course;

import io.craigmiller160.springbootstarter.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/topics/{topicId}/courses")
    public ResponseEntity<List<Course>> getAllCourses(@PathVariable String topicId) {
        return courseService.getAllCourses(topicId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
        return courseService.getCourse(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/topics/{topicId}/courses")
    public ResponseEntity<Course> addCourse(@PathVariable String topicId, @RequestBody Course course) {
        course.setTopic(new Topic(topicId, "", ""));
        return courseService.addCourse(course)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Error when trying to add topic"));
    }

    @PutMapping("/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String topicId, @PathVariable String courseId, @RequestBody Course course) {
        course.setTopic(new Topic(topicId, "", ""));
        return courseService.updateCourse(courseId, course)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/topics/{topicId}/courses/{courseId}")
    public ResponseEntity<Course> deleteCourse(@PathVariable String courseId) {
        return courseService.deleteCourse(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
