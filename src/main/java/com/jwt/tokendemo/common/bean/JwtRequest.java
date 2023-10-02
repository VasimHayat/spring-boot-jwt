package com.jwt.tokendemo.common.bean;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtRequest {

    public String username;
    public String password;
}
