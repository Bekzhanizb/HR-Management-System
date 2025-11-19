package bekezhan.io.lab7.contoller;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.mapper.UserMapper;
import bekezhan.io.lab7.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userMapper.toDTO(userService.findById(id));
    }

    @PostMapping("/register")
    public UserDTO register(
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
    public UserDTO apply(
            @RequestParam Long userId,
            @RequestParam Long vacancyId
    ) {
        return userMapper.toDTO(userService.apply(userId, vacancyId));
    }

    @PostMapping("/approve")
    public UserDTO approve(
            @RequestParam Long userId,
            @RequestParam Long vacancyId
    ) {
        return userMapper.toDTO(userService.approve(userId, vacancyId));
    }

    @PutMapping("/{id}")
    public UserDTO update(
            @PathVariable Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String password
    ) {
        UserDTO dto = new UserDTO();
        if (username != null) dto.setUsername(username);
        if (age != null) dto.setAge(age);
        if (role != null) dto.setRole(role);
        if (password != null) dto.setPassword(password);

        return userMapper.toDTO(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
