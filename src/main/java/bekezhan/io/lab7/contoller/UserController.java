package bekezhan.io.lab7.contoller;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.dto.VacancyDTO;
import bekezhan.io.lab7.mapper.UserMapper;
import bekezhan.io.lab7.mapper.VacancyMapper;
import bekezhan.io.lab7.service.UserService;
import bekezhan.io.lab7.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final UserMapper  userMapper;
    private final VacancyMapper vacancyMapper;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.getAllUsers().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
    @PostMapping("/register")
    public UserDTO registerUser(
            @RequestParam String username,
            @RequestParam int age,
            @RequestParam String role,
            @RequestParam String password
    ) {
        UserDTO dto = new UserDTO();
        dto.setUsername(username);
        dto.setAge(age);
        dto.setRole(role);
        dto.setPassword(password);
        return userMapper.toDTO(userService.register(dto));
    }

    @PostMapping("/apply")
    public UserDTO applyToVacancy(@RequestParam Long userId,
                                  @RequestParam Long vacancyId)
    {
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
    public List<VacancyDTO> getAllVacancies() {
        return vacancyService.findAll().stream()
                .map(vacancyMapper::toVacancyDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(
            @PathVariable Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String password
    ) {
        UserDTO dto = new UserDTO();
        dto.setUsername(username);
        if (age != null) {
            dto.setAge(age);
        }
        dto.setRole(role);
        dto.setPassword(password);
        return userMapper.toDTO(userService.updateUser(id, dto));
    }
}
