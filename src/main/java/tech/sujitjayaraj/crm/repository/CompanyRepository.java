package tech.sujitjayaraj.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
