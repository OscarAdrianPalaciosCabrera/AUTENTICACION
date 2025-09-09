package com.crediya.api.mapper;

import com.crediya.api.dto.ApplicantDTO;
import com.crediya.api.dto.CreateApplicantDTO;
import com.crediya.model.applicant.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ObjectFactory;
import org.springframework.context.annotation.EnableMBeanExport;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface ApplicantDtoMapper {
    @ObjectFactory
    default Applicant toModel(CreateApplicantDTO dto) {

        if (dto == null) return null;
        return Applicant.create(
                dto.name(),
                dto.lastName(),
                dto.identityDocument(),
                dto.birthDate(),
                dto.address(),
                dto.phoneNumber(),
                dto.email(),
                dto.baseSalary()
        );
    }
    ApplicantDTO toResponse(Applicant applicant);

    /*@Mappings({
            //@Mapping(target= "name", source="name"),
            //@Mapping(target = "lastName", source="lastName"),
            //@Mapping(target= "birthDate", source="birthDate"),
            //@Mapping(target= "address", source="address"),
            //@Mapping(target= "phoneNumber", source="phoneNumber"),
            //@Mapping(target= "email", source="email"),
            //@Mapping(target= "baseSalary", source="baseSalary"),
    })*/
    //Applicant toModel(CreateApplicantDTO createApplicantDTO);


}


   //

