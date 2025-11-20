package bekezhan.io.lab7.mapper;

import bekezhan.io.lab7.dto.UserDTO;
import bekezhan.io.lab7.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "username", target = "name")
    UserDTO toDTO(User user);

    @Mapping(source = "name", target = "username")
    User toEntity(UserDTO dto);
}
