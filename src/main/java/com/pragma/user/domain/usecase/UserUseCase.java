package com.pragma.user.domain.usecase;

import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IFoodCourtExternalService;
import com.pragma.user.domain.spi.IJwtSecurityServicePort;
import com.pragma.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IFoodCourtExternalService foodCourtExternalService;
    private final IJwtSecurityServicePort jwtSecurityServicePort;

    @Override
    public User saveOwner(User user) {
        user.validateDocumentId();
        user.validateCellPhoneNumber();
        user.validateAge();

        return userPersistencePort.save(user);
    }

    @Override
    public User saveEmployee(User user) {
        user.validateDocumentId();
        user.validateCellPhoneNumber();

        String tokenEmail = jwtSecurityServicePort.getSubject();
        User ownerUser = userPersistencePort.findByEmail(tokenEmail);
        User savedEmployee =  userPersistencePort.save(user);

        foodCourtExternalService.assignEmployeeToRestaurant(savedEmployee.getId(), ownerUser.getId());

        return savedEmployee;
    }

    @Override
    public User saveCustomer(User user) {
        user.validateDocumentId();
        user.validateCellPhoneNumber();

        return userPersistencePort.save(user);
    }

    @Override
    public boolean userHasRole(Long userId, String roleName) {
        User user = userPersistencePort.findById(userId);
        String userRole = user.getRole().getName();

        return roleName.equals(userRole);
    }

    @Override
    public boolean userHasEmail(Long userId, String email) {
        User user = userPersistencePort.findById(userId);
        String userEmail = user.getEmail();

        return email.equals(userEmail);
    }
}
