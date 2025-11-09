package bekezhan.io.lab7.repository;

import bekezhan.io.lab7.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentRepo extends JpaRepository<Employment, Long> {
}
