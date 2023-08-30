package com.logicea.card.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.logicea.card.entity.Task;
import com.logicea.card.entity.User;
import com.logicea.card.payload.request.LoginRequest;
import com.logicea.card.payload.request.SignupRequest;
import com.logicea.card.payload.response.JwtResponse;
import com.logicea.card.payload.response.MessageResponse;
import com.logicea.card.repository.UserRepository;
import com.logicea.card.security.jwt.JwtUtils;
import com.logicea.card.security.services.UserDetailsImpl;
import com.logicea.card.service.UserService;
import com.logicea.card.service.UserServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Email is already in use!"));
      }

      User user = new User();
           user.setEmail(signUpRequest.getEmail());
           user.setUsername(signUpRequest.getEmail()); // username is same as email
           user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
           user.setUserRole(signUpRequest.getRole()); 
           user.setUserCreatedDate(new Date());

      userRepository.saveAndFlush(user);

      return ResponseEntity.ok(new MessageResponse("user registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

      /*  User user = userRepository.findByEmail(loginRequest.getUsername());
        if(user == null) {
          return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Invalid credentials"));
        }
        // validate pwd 

         String jwt = jwtUtils.createJwtToken(user);
        return ResponseEntity.ok(new JwtResponse(jwt, 
                         user.getUserId(), 
                         user.getUsername(), 
                         user.getEmail()));

     */

      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
    }
}
