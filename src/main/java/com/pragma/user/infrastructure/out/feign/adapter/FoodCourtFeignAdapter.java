package com.pragma.user.infrastructure.out.feign.adapter;

import com.pragma.user.domain.spi.IFoodCourtExternalService;
import com.pragma.user.infrastructure.out.feign.client.FoodCourtFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodCourtFeignAdapter implements IFoodCourtExternalService {

    private final FoodCourtFeignClient foodCourtFeignClient;

    @Override
    public void assignEmployeeToRestaurant(Long employeeId, Long ownerId) {
        foodCourtFeignClient.assignEmployeeToRestaurant(employeeId, ownerId);
    }
}
