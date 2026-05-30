package com.niranjan.userservice.Controller;

import com.niranjan.commonlib.Dto.AppUserDto;
import com.niranjan.commonlib.Dto.JwtUtil;
import com.niranjan.commonlib.Dto.UserCreatedEvent;
import com.niranjan.commonlib.Dto.UserDto;

import com.niranjan.userservice.Entity.AppUser;
import com.niranjan.userservice.EventProducer.UserEventProducer;
import com.niranjan.userservice.JwtDto.LoginRequest;
import com.niranjan.userservice.JwtDto.LoginResponse;
import com.niranjan.userservice.Mapper.UserMapper;
import com.niranjan.userservice.Repository.UserRepository;
import com.niranjan.userservice.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserEventProducer userEventProducer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper ;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public AppUserDto createUser(@RequestBody AppUserDto user) {
        AppUser appUser =  userMapper.toEntity(user);
        AppUser savedUser = userRepository.save(appUser);

        //AppUser savedUser = userRepository.save(user);
        // Convert entity → event DTO
        UserCreatedEvent event = new UserCreatedEvent(savedUser.getId(), savedUser.getName(),savedUser.getEmail());
        userEventProducer.sendUserCreatedEvent(event);  // pass event, not entity

        return userMapper.toDto(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        AppUser appUser = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!appUser.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("INVALID CREDENTIALS"));
        }

        // ✅ Generate JWT token using role from DB ("USER" or "ADMIN")
        String token = jwtUtil.generateToken(appUser.getEmail(), appUser.getRole());

        // ✅ Return token to frontend
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("{id}")
    public AppUserDto getUser(@PathVariable("id") Long id) throws InterruptedException {
       // Thread.sleep(5000);
        AppUser entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setOrderCount(entity.getOrderCount());
        return dto;
    }
}
