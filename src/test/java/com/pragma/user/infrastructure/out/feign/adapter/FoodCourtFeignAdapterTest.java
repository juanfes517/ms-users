package com.pragma.user.infrastructure.out.feign.adapter;

import com.pragma.user.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
import com.pragma.user.infrastructure.out.feign.client.FoodCourtFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodCourtFeignAdapterTest {

    @InjectMocks
    private FoodCourtFeignAdapter foodCourtFeignAdapter;

    @Mock
    private FoodCourtFeignClient foodCourtFeignClient;

    @Test
    void assignEmployeeToRestaurant_WhenThrowRestaurantNotFoundException() {
        Long employeeId = 1L;
        Long ownerId = 2L;
        boolean isSuccessful = false;

        when(foodCourtFeignClient.assignEmployeeToRestaurant(employeeId, ownerId))
                .thenReturn(isSuccessful);

        RestaurantNotFoundException result = assertThrows(RestaurantNotFoundException.class, () -> foodCourtFeignAdapter.assignEmployeeToRestaurant(employeeId, ownerId));

        assertEquals(ExceptionConstants.RESTAURANT_NOT_FOUND, result.getMessage());
    }
}