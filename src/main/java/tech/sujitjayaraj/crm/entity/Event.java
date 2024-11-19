package tech.sujitjayaraj.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Type type;

    private String title;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    private Instant time;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.EAGER)
    private List<Notification> notifications;

    public enum Type {}
}
