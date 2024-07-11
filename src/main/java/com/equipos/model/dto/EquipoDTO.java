package com.equipos.model.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
public class EquipoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4942677557450959140L;

    private Long id;
    private String nombre;
    private String liga;
    private String pais;
}
