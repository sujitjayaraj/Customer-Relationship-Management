package tech.sujitjayaraj.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_id")
    private Event event;

    private Instant created;

    private Boolean wasRead;

    private String content;

    @PrePersist
    protected void setCreated() {
        this.created = Instant.now();
    }
}
