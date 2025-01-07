package tech.sujitjayaraj.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByNameIgnoreCase(String name);

    List<Client> findByUser(User user);

    List<Client> findByUserIn(List<User> users);

    List<Client> findByStatusOrderByNameAsc(Client.Status status);

    List<Client> findByNameContainingIgnoreCaseOrderByName(String name);

    List<Client> findByAddressCityOrderByNameAsc(String city);

    List<Client> findByStatusAndAddressCityOrderByNameAsc(Client.Status status, String city);

    List<Client> findByNameContainingIgnoreCaseAndAddressCityOrderByNameAsc(String name, String city);

}
