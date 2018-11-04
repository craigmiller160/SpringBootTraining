package io.craigmiller160.springbootstarter.topic.jdbc;

import io.craigmiller160.springbootstarter.topic.Topic;
import io.craigmiller160.springbootstarter.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JdbcTopicService implements TopicService {

    private static final String SELECT_ALL_TOPICS = "SELECT * FROM topic";
    private static final String SELECT_ONE_TOPIC = "SELECT * FROM topic WHERE id = ?";
    private static final String INSERT_TOPIC = "INSERT INTO topic (id, name, description) VALUES (?,?,?)";
    private static final String UPDATE_TOPIC = "UPDATE topic SET name = ?, description = ? WHERE id = ?";
    private static final String DELETE_TOPIC = "DELETE FROM topic WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTopicService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Optional<List<Topic>> getAllTopics() {
        List<Topic> topics = jdbcTemplate.query(SELECT_ALL_TOPICS, Topic.ROW_MAPPER);
        if (topics.size() == 0){
            return Optional.empty();
        }
        return Optional.of(topics);
    }

    @Transactional
    @Override
    public Optional<Topic> getTopic(String topicId) {
        List<Topic> topics = jdbcTemplate.query(SELECT_ONE_TOPIC, new Object[] {topicId}, Topic.ROW_MAPPER);
        if (topics.size() == 0) {
            return Optional.empty();
        }
        else if (topics.size() > 1) {
            throw new RuntimeException("Database error occurred. Too many topics with the same primary key were returned. Size: " + topics.size());
        }

        return Optional.of(topics.get(0));
    }

    @Transactional
    @Override
    public Optional<Topic> addTopic(Topic topic) {
        int result = jdbcTemplate.update(INSERT_TOPIC, topic.getId(), topic.getName(), topic.getDescription());
        if (result == 0) {
            return Optional.empty();
        }
        return Optional.of(topic);
    }

    @Transactional
    @Override
    public Optional<Topic> updateTopic(String topicId, Topic topic) {
        topic.setId(topicId);
        int result = jdbcTemplate.update(UPDATE_TOPIC, topic.getName(), topic.getDescription(), topicId);
        if (result == 0) {
            return Optional.empty();
        }

        return Optional.of(topic);
    }

    @Transactional
    @Override
    public Optional<Topic> deleteTopic(String topicId) {
        List<Topic> topics = jdbcTemplate.query(SELECT_ONE_TOPIC, new Object[]{topicId}, Topic.ROW_MAPPER);
        if (topics.size() == 0) {
            return Optional.empty();
        }
        int result = jdbcTemplate.update(DELETE_TOPIC, topicId);
        if (result == 0) {
            return Optional.empty();
        }
        return Optional.of(topics.get(0));
    }
}
