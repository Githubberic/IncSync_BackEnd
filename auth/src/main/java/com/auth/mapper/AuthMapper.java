package com.auth.mapper;

import com.auth.dto.request.AuthCreateRequestDTO;
import com.auth.model.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
   AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth toUser(AuthCreateRequestDTO user);

}
