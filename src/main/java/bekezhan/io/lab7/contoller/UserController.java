package bekezhan.io.lab7.contoller;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.Vacancy;
import bekezhan.io.lab7.mapper.UserMapper;
import bekezhan.io.lab7.service.UserService;
import bekezhan.io.lab7.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService userService;
    private VacancyService vacancyService;
    private UserMapper  userMapper;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.getAllUsers().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userMapper.toDTO(userService.register(userDTO));
    }
    @PostMapping("/apply")
    public UserDTO applyToVacancy(
            @RequestParam Long userId,
            @RequestParam Long vacancyId
    ) {
        return userMapper.toDTO(userService.applyToVacancy(userId, vacancyId));
    }
    @PostMapping("/approve")
    public UserDTO approveUserForVacancy(
            @RequestParam Long userId,
            @RequestParam Long vacancyId
    ) {
        return userMapper.toDTO(userService.approveUserToVacancy(userId, vacancyId));
    }
    @GetMapping("/vacancies")
    public List<Vacancy> getAllVacancies() {
        return vacancyService.findAll();
    }
}
