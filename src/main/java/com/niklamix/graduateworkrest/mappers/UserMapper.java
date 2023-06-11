package com.niklamix.graduateworkrest.mappers;

import com.niklamix.graduateworkrest.dto.UserReadDTO;
import com.niklamix.graduateworkrest.dto.UserWriteDTO;
import com.niklamix.graduateworkrest.entity.User;
import com.niklamix.graduateworkrest.filters.UserFilter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserWriteDTO userWriteDTO);
    UserReadDTO toUserReadDTO(User user);
    User toUserWithFilter(UserFilter userFilter);

}
