package com.nonso.trackerApp.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private String username;
    private String email;
    private String password;
}
