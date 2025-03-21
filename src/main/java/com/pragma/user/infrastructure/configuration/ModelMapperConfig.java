package com.pragma.user.infrastructure.configuration;

import com.pragma.user.application.dto.request.CustomerRequestDto;
import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.domain.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<OwnerRequestDto, User>() {
            @Override
            protected void configure() {
                map().setDocumentId(source.getDocumentId());
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<EmployeeRequestDto, User>() {
            @Override
            protected void configure() {
                map().setDocumentId(source.getDocumentId());
                skip(destination.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<CustomerRequestDto, User>() {
            @Override
            protected void configure() {
                map().setDocumentId(source.getDocumentId());
                skip(destination.getId());
            }
        });

        return modelMapper;
    }
}
