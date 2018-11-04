package io.craigmiller160.springbootstarter.topic;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Topic {

    public static RowMapper<Topic> ROW_MAPPER = (rs, rowNum) -> {
        Topic topic = new Topic();
        topic.setId(rs.getString("id"));
        topic.setName(rs.getString("name"));
        topic.setDescription(rs.getString("description"));

        return topic;
    };

    @Id
    private String id;
    private String name;
    private String description;

    public Topic() { }

    public Topic(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id) &&
                Objects.equals(name, topic.name) &&
                Objects.equals(description, topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Topic: " + name;
    }
}
