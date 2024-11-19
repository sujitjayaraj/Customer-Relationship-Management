package tech.sujitjayaraj.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String fileName;

    private Instant created;

    private Boolean accepted;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "accepted_by_id")
    private User acceptedBy;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Double value;

    @PrePersist
    private void setCreated() {
        this.created = Instant.now();
    }
}
