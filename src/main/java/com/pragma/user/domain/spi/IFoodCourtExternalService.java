package com.pragma.user.domain.spi;

public interface IFoodCourtExternalService {

    boolean assignEmployeeToRestaurant(Long employeeId, Long ownerId);
}
