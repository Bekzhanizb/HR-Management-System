package bekezhan.io.lab7.service;

import bekezhan.io.lab7.entity.User;
import bekezhan.io.lab7.entity.Vacancy;
import bekezhan.io.lab7.repository.UserRepo;
import bekezhan.io.lab7.repository.VacancyRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepo;
    private VacancyRepo vacancyRepo;

    public User applyToVacancy(Long userId, Long vacancyId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow(() -> new EntityNotFoundException("Vacancy not found"));
        vacancy.getApplications().add(user);
        return vacancyRepo.save(vacancy)
                .getApplications()
                .stream().filter(u -> u.getId().equals(userId))
                .findFirst().orElse(user);
    }

    public User approveUserToVacancy(Long userId, Long vacancyId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Vacancy vacancy = vacancyRepo.findById(vacancyId).orElseThrow(() -> new EntityNotFoundException("Vacancy not found"));
        user.setVacancy(vacancy);
        vacancy.setOpen(false);
        vacancyRepo.save(vacancy);
        return userRepo.save(user);
    }
}
