package io.craigmiller160.springbootstarter.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course,String> {

    List<Course> findByTopicId(String topicId);

    Optional<Course> findByTopicIdAndId(String topicId, String courseId);

    void deleteByTopicIdAndId(String topicId, String courseId);

}
