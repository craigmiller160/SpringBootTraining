package io.craigmiller160.springbootstarter.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    public Optional<List<Topic>> getAllTopics() {
        List<Topic> topics = StreamSupport.stream(topicRepository.findAll().spliterator(),  false)
                .collect(Collectors.toList());

        if (topics.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(topics);
    }

    @Transactional
    public Optional<Topic> getTopic(String id) {
        return topicRepository.findById(id);
    }

    @Transactional
    public Optional<Topic> addTopic(Topic topic) {
        topic = topicRepository.save(topic); //TODO this creates and updates together... wtf...
        return Optional.of(topic);
    }

    @Transactional
    public Optional<Topic> updateTopic(String id, Topic topic) {
        topic = topicRepository.save(topic);
        return Optional.of(topic); //TODO this doesn't distinguish between something new or something updated
    }

    @Transactional
    public Optional<Topic> deleteTopic(String id) {
        Optional<Topic> topic = topicRepository.findById(id);
        topicRepository.deleteById(id); //TODO don't like how this doesn't return anything...
        return topic;
    }

}
