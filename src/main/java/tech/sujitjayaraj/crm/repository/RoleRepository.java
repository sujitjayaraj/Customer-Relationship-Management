package tech.sujitjayaraj.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sujitjayaraj.crm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
