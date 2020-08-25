package takip.back.mapper;

import org.mapstruct.Mapper;
import takip.back.DTO.UsersDTO;
import takip.back.entity.Users;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDTO mapToDTO(Users users);

    Users mapToEntity(UsersDTO usersDTO);

    List<UsersDTO> mapToDTO(List<Users> users);

    List<Users> mapToEntity(List<UsersDTO> usersDTO);
}
