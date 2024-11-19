package tech.sujitjayaraj.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(targetEntity = User.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "office")
    private List<User> employees;
}
