package com.user.mapper;

import com.user.dto.request.UserCreateRequestDTO;
import com.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toUser(UserCreateRequestDTO user);

}
