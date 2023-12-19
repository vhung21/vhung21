package com.example.learncode5.payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private String email;
    private List<String> roles;

}

