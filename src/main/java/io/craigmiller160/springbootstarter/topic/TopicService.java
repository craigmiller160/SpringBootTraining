package io.craigmiller160.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class TopicService {

    private final List<Topic> topics = new ArrayList<>(List.of(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Core Java Description"),
            new Topic("javascript", "JavaScript", "JavaScript Description")
    ));

    public Optional<List<Topic>> getAllTopics() {
        if (topics.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(topics);
    }

    public Optional<Topic> getTopic(String id) {
        return topics.stream()
                .filter(topic -> topic.getId().equals(id))
                .findFirst();
    }

    public Optional<Topic> addTopic(Topic topic) {
        topics.add(topic);
        return Optional.of(topic);
    }

    private int findTopicIndex(String id) {
        return IntStream.range(0, topics.size())
                .filter(index -> topics.get(index).getId().equals(id))
                .findFirst()
                .orElse(-1);
    }

    public Optional<Topic> updateTopic(String id, Topic topic) {
        int topicIndex = findTopicIndex(id);
        if (topicIndex == -1) {
            return Optional.empty();
        }
        topics.set(topicIndex, topic);
        return Optional.of(topic);
    }

    public Optional<Topic> deleteTopic(String id) {
        int topicIndex = findTopicIndex(id);
        if (topicIndex == -1) {
            return Optional.empty();
        }
        Topic removedTopic = topics.remove(topicIndex);
        return Optional.of(removedTopic);
    }

}
