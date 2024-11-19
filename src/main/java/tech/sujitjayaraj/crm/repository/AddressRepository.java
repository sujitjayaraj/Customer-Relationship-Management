package tech.sujitjayaraj.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
