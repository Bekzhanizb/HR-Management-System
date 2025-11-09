package bekezhan.io.lab7.repository;

import bekezhan.io.lab7.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepo extends JpaRepository<Application, Long> {
}
