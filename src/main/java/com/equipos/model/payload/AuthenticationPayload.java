package com.equipos.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Validated
public class AuthenticationPayload implements Serializable {

    @Serial
    private static final long serialVersionUID = -9146708942350801913L;

    @JsonProperty("username")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
    @JsonProperty("password")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtPayload{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
