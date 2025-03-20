package com.pragma.user.domain.spi;

public interface IFoodCourtExternalService {

    void assignEmployeeToRestaurant(Long employeeId, Long ownerId);
}
