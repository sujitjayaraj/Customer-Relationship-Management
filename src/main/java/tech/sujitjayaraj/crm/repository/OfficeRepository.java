package tech.sujitjayaraj.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sujitjayaraj.crm.entity.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}
