package com.jwt.tokendemo.common.service;

import com.jwt.tokendemo.common.dto.req.UserRegisterReqDto;
import com.jwt.tokendemo.common.model.EOUser;
import com.jwt.tokendemo.common.model.EOUserRole;
import com.jwt.tokendemo.common.repositories.EORoleRepository;
import com.jwt.tokendemo.common.repositories.EOUserRepository;
import com.jwt.tokendemo.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EOUserService {


    @Autowired
    private EOUserRepository userRepository;

    @Autowired
    private EORoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EOUser createNewUser(UserRegisterReqDto reqDto){
        EOUser eoUser =null;
        try{
            eoUser = EOUser.builder()
                    .firstName(reqDto.getFirstName())
                    .lastName(reqDto.getLastName())
                    .email(reqDto.getEmail())
                    .password(passwordEncoder.encode(reqDto.getPassword()))
                    .dob(DateUtil.stringToLocalDate(reqDto.getDob())).build();
            System.out.println(eoUser);
            EOUserRole eoUserRole = new EOUserRole();
            eoUserRole.setEoUser(eoUser);
            eoUserRole.setEoRole(roleRepository.getUserRole());
            eoUser.setEoUserRoleArray(Collections.singleton(eoUserRole));

            System.out.println("##################### ");
            System.out.println(eoUser);
            System.out.println("##################### ");
            userRepository.save(eoUser);
        }catch (Exception e){
            e.printStackTrace();
        }


        return eoUser;
    }

    public List<EOUser> getAllUsers() {
        return userRepository.findAll();
    }
}
