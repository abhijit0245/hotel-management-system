package com.HotelManagementSystem.service.impl;


import com.HotelManagementSystem.dto.AuthRequest;
import com.HotelManagementSystem.dto.AuthResponse;
import com.HotelManagementSystem.dto.RegisterRequest;
import com.HotelManagementSystem.entity.User;
import com.HotelManagementSystem.repository.UserRepository;
import com.HotelManagementSystem.service.UserService;


import com.HotelManagementSystem.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAdmin(request.getIsAdmin());

        userRepository.save(user);
        System.out.println("RegisterRequest isAdmin: " + request.getIsAdmin());
        System.out.println("User to be saved isAdmin: " + user.isAdmin());

    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.isAdmin());
        return new AuthResponse(token);
    }
}
