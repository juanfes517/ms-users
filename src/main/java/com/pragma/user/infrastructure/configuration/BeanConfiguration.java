package com.pragma.user.infrastructure.configuration;

import com.pragma.user.domain.api.IAuthServicePort;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.spi.*;
import com.pragma.user.domain.usecase.AuthUseCase;
import com.pragma.user.domain.usecase.RoleUseCase;
import com.pragma.user.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort, IFoodCourtExternalService foodCourtExternalService, IJwtSecurityServicePort jwtSecurityServicePort) {
        return new UserUseCase(userPersistencePort, foodCourtExternalService, jwtSecurityServicePort);
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
