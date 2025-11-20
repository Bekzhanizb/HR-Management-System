package bekezhan.io.lab7.service;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.User;

import java.util.List;


public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User register(UserDTO dto);
    User update(Long id, UserDTO dto);
    boolean delete(Long id);
}
