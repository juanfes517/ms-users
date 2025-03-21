package com.pragma.user.infrastructure.out.feign.client;

import com.pragma.user.infrastructure.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${FOOD_COURT_MICROSERVICE_NAME}", url = "${FOOD_COURT_MICROSERVICE_HOST}", configuration = FeignClientConfiguration.class)
public interface FoodCourtFeignClient {

    @PostMapping
    Boolean assignEmployeeToRestaurant(@RequestParam Long employeeId, @RequestParam Long ownerId);
}
