package tech.sujitjayaraj.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String fileName;

    private Instant created;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id")
    private User author;

    @PrePersist
    private void setCreated() {
        this.created = Instant.now();
    }
}
