package tech.sujitjayaraj.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
