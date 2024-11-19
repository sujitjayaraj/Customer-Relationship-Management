package tech.sujitjayaraj.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    private String status;

    private Instant created;

    @Email
    @Column(unique = true)
    private String email;

    private String phone;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @PrePersist
    private void setCreated() {
        this.created = Instant.now();
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }
}
