package com.niranjan.userservice.Mapper;

import com.niranjan.commonlib.Dto.AppUserDto;
import com.niranjan.commonlib.Dto.InventoryDto;
import com.niranjan.userservice.Entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public AppUserDto toDto(AppUser appUser) {
        return new AppUserDto(appUser.getId(), appUser.getName(), appUser.getEmail(), appUser.getPassword(),appUser.getRole(),appUser.getOrderCount());

    }
    public AppUser toEntity(AppUserDto appUserDto) {
        return new AppUser(appUserDto.getId(),appUserDto.getName(),
                appUserDto.getEmail(),appUserDto.getPassword(),
                appUserDto.getRole(),appUserDto.getOrderCount());


    }
}
