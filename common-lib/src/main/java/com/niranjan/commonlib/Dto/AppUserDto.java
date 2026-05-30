package com.niranjan.commonlib.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private int orderCount;
    // getters and setters
}