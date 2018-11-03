package io.craigmiller160.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final List<Topic> topics = List.of(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Core Java Description"),
            new Topic("javascript", "JavaScript", "JavaScript Description")
    );

    public List<Topic> getAllTopics() {
        return topics;
    }

}
