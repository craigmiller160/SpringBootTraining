package io.craigmiller160.springbootstarter.topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    Optional<List<Topic>> getAllTopics();

    Optional<Topic> getTopic(String topicId);

    Optional<Topic> addTopic(Topic topic);

    Optional<Topic> updateTopic(String topicId, Topic topic);

    Optional<Topic> deleteTopic(String topicId);

}
