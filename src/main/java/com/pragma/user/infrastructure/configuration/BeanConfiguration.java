package com.pragma.user.infrastructure.configuration;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.domain.api.IAuthServicePort;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IAuthSecurityPort;
import com.pragma.user.domain.spi.IJwtSecurityServicePort;
import com.pragma.user.domain.spi.IRolePersistencePort;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.domain.usecase.AuthUseCase;
import com.pragma.user.domain.usecase.RoleUseCase;
import com.pragma.user.domain.usecase.UserUseCase;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public IRoleServicePort roleServicePort(IRolePersistencePort rolePersistencePort) {
        return new RoleUseCase(rolePersistencePort);
    }

    @Bean
    public IAuthServicePort authServicePort(IJwtSecurityServicePort jwtSecurityServicePort, IAuthSecurityPort authSecurityPort) {
        return new AuthUseCase(jwtSecurityServicePort, authSecurityPort);
    }
}
