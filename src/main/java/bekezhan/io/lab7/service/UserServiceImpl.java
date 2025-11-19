package bekezhan.io.lab7.service;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.User;
import bekezhan.io.lab7.entity.Vacancy;
import bekezhan.io.lab7.mapper.UserMapper;
import bekezhan.io.lab7.repository.UserRepo;
import bekezhan.io.lab7.repository.VacancyRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final VacancyRepo vacancyRepo;
    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User register(UserDTO user) {
        return userRepo.save(userMapper.toEntity(user));
    }

    public User apply(Long userId, Long vacancyId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Vacancy vacancy = vacancyRepo.findById(vacancyId)
                .orElseThrow(() -> new EntityNotFoundException("Vacancy not found"));

        vacancy.getApplications().add(user);
        vacancyRepo.save(vacancy);

        return user;
    }

    public User approve(Long userId, Long vacancyId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Vacancy vacancy = vacancyRepo.findById(vacancyId)
                .orElseThrow(() -> new EntityNotFoundException("Vacancy not found"));

        user.setVacancy(vacancy);
        vacancy.setOpen(false);

        vacancyRepo.save(vacancy);
        return userRepo.save(user);
    }

    public User update(Long id, UserDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getAge() > 0) user.setAge(dto.getAge());
        if (dto.getRole() != null) user.setRole(dto.getRole());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());

        return userRepo.save(user);
    }

    @Override
    public boolean delete(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
