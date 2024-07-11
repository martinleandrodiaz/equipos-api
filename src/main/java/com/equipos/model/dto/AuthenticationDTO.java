package com.equipos.model.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
public class AuthenticationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1534085033805915993L;

    private String token;
}
