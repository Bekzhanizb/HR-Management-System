package bekezhan.io.lab7.service;

import bekezhan.io.lab7.entity.Vacancy;
import bekezhan.io.lab7.repository.VacancyRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {
    private VacancyRepo vacancyRepo;

    public List<Vacancy> findAll() {
        return vacancyRepo.findAll();
    }
    public Vacancy findById(Long id) {
        return vacancyRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Vacancy not found"));
    }

    public Vacancy save(Vacancy vacancy) {
        return vacancyRepo.save(vacancy);
    }
}
