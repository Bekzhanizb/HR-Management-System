package bekezhan.io.lab7.repository;

import bekezhan.io.lab7.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
}
