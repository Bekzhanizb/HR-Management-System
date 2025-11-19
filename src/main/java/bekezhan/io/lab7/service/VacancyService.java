package bekezhan.io.lab7.service;

import bekezhan.io.lab7.dto.VacancyDTO;
import bekezhan.io.lab7.entity.Vacancy;
import bekezhan.io.lab7.repository.VacancyRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepo vacancyRepo;

    public List<Vacancy> findAll() {
        return vacancyRepo.findAll();
    }

    public Vacancy findById(Long id) {
        return vacancyRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vacancy not found"));
    }

    public Vacancy create(Vacancy vacancy) {
        return vacancyRepo.save(vacancy);
    }

    public Vacancy update(Long id, VacancyDTO dto) {
        Vacancy vacancy = findById(id);

        if (dto.getTitle() != null) vacancy.setTitle(dto.getTitle());
        if (dto.getSalary() != 0) vacancy.setSalary(dto.getSalary());
        if (dto.isOpen() != vacancy.isOpen()) vacancy.setOpen(dto.isOpen());

        return vacancyRepo.save(vacancy);
    }
    public void delete(Long id) {
        vacancyRepo.deleteById(id);
    }
}
