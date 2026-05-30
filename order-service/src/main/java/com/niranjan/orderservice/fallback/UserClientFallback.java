package com.niranjan.orderservice.fallback;

import com.niranjan.commonlib.Dto.AppUserDto;
import com.niranjan.orderservice.client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public AppUserDto getUserById(Long id, String token) {
        AppUserDto fallbackUser = new AppUserDto();
        fallbackUser.setId(id);
        fallbackUser.setName("Fallback User");
        fallbackUser.setEmail("unavailable@example.com");
        return fallbackUser;
    }

}
