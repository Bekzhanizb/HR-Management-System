package bekezhan.io.lab7.service;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.User;
import bekezhan.io.lab7.entity.Vacancy;
import bekezhan.io.lab7.repository.UserRepo;
import bekezhan.io.lab7.repository.VacancyRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;
    private VacancyRepo vacancyRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User register(UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setAge(dto.getAge());
        user.setRole(dto.getRole());
        user.setPassword(user.getPassword());
        return userRepo.save(user);
    }
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
