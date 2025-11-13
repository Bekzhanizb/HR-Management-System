package bekezhan.io.lab7.mapper;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.User;
import bekezhan.io.lab7.entity.Vacancy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setAge(user.getAge());
        dto.setRole(user.getRole());
        dto.setPassword(user.getPassword());
        dto.setVacancyId(user.getVacancy() != null ? user.getVacancy().getId() : null);
        return dto;
    }
    public User toEntity(UserDTO dto, Vacancy vacancy) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setAge(dto.getAge());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        user.setVacancy(vacancy);
        return user;
    }
}
