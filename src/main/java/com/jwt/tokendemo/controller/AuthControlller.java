package com.jwt.tokendemo.controller;

import com.jwt.tokendemo.common.dto.req.UserRegisterReqDto;
import com.jwt.tokendemo.common.model.EOUser;
import com.jwt.tokendemo.common.repositories.EOUserRepository;
import com.jwt.tokendemo.common.service.EOUserService;
import com.jwt.tokendemo.security.JwtUtil;
import com.jwt.tokendemo.common.bean.JwtRequest;
import com.jwt.tokendemo.common.bean.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthControlller {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EOUserService eoUserService;

    @Autowired
    private EOUserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(jwtUtil.generateToken(userDetails.getUsername()))
                    .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<EOUser> register(@RequestBody UserRegisterReqDto request) {
        EOUser eoUser = userRepository.getUserByEmail(request.getEmail());

        if(eoUser == null){
            eoUser = this.eoUserService.createNewUser(request);
            return new ResponseEntity<>(eoUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(eoUser, HttpStatus.OK);
        }


    }

    @GetMapping("/user")
    public String users () {
      return "THis istest use...";
    }
}
