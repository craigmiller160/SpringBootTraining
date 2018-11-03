package io.craigmiller160.springbootstarter.topic.jpa;

import io.craigmiller160.springbootstarter.topic.Topic;
import org.springframework.data.repository.CrudRepository;

public interface JpaTopicRepository extends CrudRepository<Topic,String> {

    

}
