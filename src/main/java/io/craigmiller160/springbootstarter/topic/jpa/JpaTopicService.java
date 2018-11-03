package io.craigmiller160.springbootstarter.topic.jpa;

import io.craigmiller160.springbootstarter.topic.Topic;
import io.craigmiller160.springbootstarter.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JpaTopicService implements TopicService {

    private final JpaTopicRepository topicRepository;

    @Autowired
    public JpaTopicService(JpaTopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    @Override
    public Optional<List<Topic>> getAllTopics() {
        List<Topic> topics = StreamSupport.stream(topicRepository.findAll().spliterator(),  false)
                .collect(Collectors.toList());

        if (topics.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(topics);
    }

    @Transactional
    @Override
    public Optional<Topic> getTopic(String topicId) {
        return topicRepository.findById(topicId);
    }

    @Transactional
    @Override
    public Optional<Topic> addTopic(Topic topic) {
        topic = topicRepository.save(topic); //TODO this creates and updates together... wtf...
        return Optional.of(topic);
    }

    @Transactional
    @Override
    public Optional<Topic> updateTopic(String topicId, Topic topic) {
        topic.setId(topicId);
        topic = topicRepository.save(topic);
        return Optional.of(topic);
    }

    @Transactional
    @Override
    public Optional<Topic> deleteTopic(String topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        topicRepository.deleteById(topicId); //TODO don't like how this doesn't return anything...
        return topic;
    }

}
