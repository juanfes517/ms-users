package com.pragma.user.infrastructure.configuration;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IRolePersistencePort;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.domain.usecase.RoleUseCase;
import com.pragma.user.domain.usecase.UserUseCase;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort) {
        return new UserUseCase(userPersistencePort);
    }

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

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IRoleServicePort roleServicePort(IRolePersistencePort rolePersistencePort) {
        return new RoleUseCase(rolePersistencePort);
    }
}
