package io.craigmiller160.springbootstarter.topic;

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
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return topicService.getAllTopics()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable String id) {
        return topicService.getTopic(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/topics")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic) {
        return topicService.addTopic(topic)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Error when trying to add topic"));
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        return topicService.updateTopic(id, topic)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable String id) {
        return topicService.deleteTopic(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
