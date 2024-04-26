package com.codegym.app.service.mapper;

import com.codegym.app.model.dto.UpdateUserDto;
import com.codegym.app.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUserFromUserDto(UpdateUserDto dto, @MappingTarget User user);
}
