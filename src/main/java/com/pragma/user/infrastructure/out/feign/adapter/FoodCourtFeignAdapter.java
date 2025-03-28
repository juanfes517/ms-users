package com.pragma.user.infrastructure.out.feign.adapter;

import com.pragma.user.domain.spi.IFoodCourtExternalService;
import com.pragma.user.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
import com.pragma.user.infrastructure.out.feign.client.FoodCourtFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodCourtFeignAdapter implements IFoodCourtExternalService {

    private final FoodCourtFeignClient foodCourtFeignClient;

    @Override
    public void assignEmployeeToRestaurant(Long employeeId, Long ownerId) {
        boolean isSuccessful = foodCourtFeignClient.assignEmployeeToRestaurant(employeeId, ownerId);

        if (!isSuccessful) {
            throw new RestaurantNotFoundException(ExceptionConstants.RESTAURANT_NOT_FOUND);
        }
    }
}
