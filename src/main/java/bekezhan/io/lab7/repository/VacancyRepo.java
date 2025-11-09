package bekezhan.io.lab7.repository;

import bekezhan.io.lab7.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
}
