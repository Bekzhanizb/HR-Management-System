package bekezhan.io.lab7.mapper;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
