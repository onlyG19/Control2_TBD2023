package cl.tbd.control2.backend.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String password;
}
