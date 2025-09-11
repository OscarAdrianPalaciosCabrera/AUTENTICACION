package com.crediya.api.mapper;

import com.crediya.api.dto.LoginResponseDTO;
import com.crediya.api.dto.UserDTO;
import com.crediya.api.dto.CreateUserDTO;
import com.crediya.model.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;


@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @ObjectFactory
    default User toModel(CreateUserDTO dto) {

        if (dto == null) return null;
        return User.create(
                dto.name(),
                dto.lastName(),
                dto.identityDocument(),
                dto.birthDate(),
                dto.address(),
                dto.phoneNumber(),
                dto.email(),
                dto.baseSalary(),
                dto.role(),
                dto.passwordHash()
        );
    }
    UserDTO toResponse(User user);

    /*@Mappings({
            //@Mapping(target= "name", source="name"),
            //@Mapping(target = "lastName", source="lastName"),
            //@Mapping(target= "birthDate", source="birthDate"),
            //@Mapping(target= "address", source="address"),
            //@Mapping(target= "phoneNumber", source="phoneNumber"),
            //@Mapping(target= "email", source="email"),
            //@Mapping(target= "baseSalary", source="baseSalary"),
    })*/
    //User toModel(CreateUserDTO createUserDTO);


}


//
