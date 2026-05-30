package com.niranjan.commonlib.Dto;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private Long userId;
    private String name;
    private String email;

    // Default constructor (needed for deserialization)
    public UserCreatedEvent() {}

    public UserCreatedEvent(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

}
