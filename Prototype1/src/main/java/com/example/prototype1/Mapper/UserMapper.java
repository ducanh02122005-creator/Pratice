package com.example.prototype1.Mapper;

import com.example.prototype1.DTOS.UserDto;
import com.example.prototype1.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
