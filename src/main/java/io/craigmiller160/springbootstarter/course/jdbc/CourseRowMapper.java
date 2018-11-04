package io.craigmiller160.springbootstarter.course.jdbc;

import io.craigmiller160.springbootstarter.course.Course;
import io.craigmiller160.springbootstarter.topic.Topic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getString("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));

        String topicId = rs.getString("topic_id");
        if (!StringUtils.isEmpty(topicId)) {
            Topic topic = new Topic();
            topic.setId(topicId);
            topic.setName(rs.getString("topic_name"));
            topic.setDescription(rs.getString("topic_description"));
            course.setTopic(topic);
        }

        return course;
    }
}
