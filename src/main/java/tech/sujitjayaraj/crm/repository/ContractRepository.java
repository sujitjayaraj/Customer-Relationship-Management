package tech.sujitjayaraj.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Contract;
import tech.sujitjayaraj.crm.entity.User;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByAcceptedBy(User acceptedBy);
}
