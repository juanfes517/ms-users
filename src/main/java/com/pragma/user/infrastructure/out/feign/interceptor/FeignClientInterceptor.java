package com.pragma.user.infrastructure.out.feign.interceptor;

import com.pragma.user.infrastructure.helper.jwt.TokenHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        String token = TokenHolder.getToken();
        requestTemplate.header("Authorization", "Bearer " + token);

    }
}
