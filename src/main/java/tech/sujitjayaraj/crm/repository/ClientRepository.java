package tech.sujitjayaraj.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(CONCAT(c.firstName, ' ', c.lastName)) = LOWER(:name)")
    Optional<Client> findByNameIgnoreCase(String name);

    List<Client> findByUser(User user);

    List<Client> findByUserIn(List<User> users);

}
