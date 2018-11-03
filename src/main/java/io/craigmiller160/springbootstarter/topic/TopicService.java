package io.craigmiller160.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class TopicService {

    private final List<Topic> topics = new ArrayList<>(List.of(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Core Java Description"),
            new Topic("javascript", "JavaScript", "JavaScript Description")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String id) {
        return topics.stream()
                .filter(topic -> topic.getId().equals(id))
                .findFirst()
                .get();
    }

    public Topic addTopic(Topic topic) {
        topics.add(topic);
        return topic;
    }

    public Topic updateTopic(String id, Topic topic) {
        int matchIndex = IntStream.range(0, topics.size())
                .filter(index -> topics.get(index).getId().equals(id))
                .findFirst()
                .getAsInt();
        topics.set(matchIndex, topic);
        return topic;
    }

    public Topic deleteTopic(String id) {
        int matchIndex = IntStream.range(0, topics.size())
                .filter(index -> topics.get(index).getId().equals(id))
                .findFirst()
                .getAsInt();
        return topics.remove(matchIndex);
    }

}
