package com.pragma.user.infrastructure.out.jpa.adapter;

import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import com.pragma.user.infrastructure.exception.UserNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
import com.pragma.user.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User save(User user) {
        UserEntity mappedUserEntity = modelMapper.map(user, UserEntity.class);
        mappedUserEntity.setId(null);

        UserEntity savedUserEntity = userRepository.save(mappedUserEntity);

        return modelMapper.map(savedUserEntity, User.class);
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND));

        return modelMapper.map(userEntity, User.class);
    }
}
