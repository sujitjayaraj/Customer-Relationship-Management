package tech.sujitjayaraj.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
