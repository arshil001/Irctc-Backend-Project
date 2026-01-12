package com.substring.Irctc.controller;

import com.substring.Irctc.config.security.JwtHelper;
import com.substring.Irctc.dto.ErrorResponse;
import com.substring.Irctc.dto.JwtResponse;
import com.substring.Irctc.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest){

        // token generate

        try{

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password());
            this.authenticationManager.authenticate(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());

            String token = this.jwtHelper.generateToken(userDetails);
            JwtResponse jwtResponse = new JwtResponse(
                    token,
                    userDetails.getUsername()
            );
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

        }catch(BadCredentialsException e){
            System.out.println(" invalid credentials");
            ErrorResponse response = new ErrorResponse("username or password is incorrect ","400",false);
//            return ResponseEntity.badRequest().build();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);


        }

    }
}
