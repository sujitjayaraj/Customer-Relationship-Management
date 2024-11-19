package tech.sujitjayaraj.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(targetEntity = Address.class)
    private Address address;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]$")
    private String pan;

    @OneToMany(targetEntity = Office.class, mappedBy = "company")
    private List<Office> offices;
}
