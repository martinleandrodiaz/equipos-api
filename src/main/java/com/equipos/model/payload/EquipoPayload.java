package com.equipos.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Validated
@ToString
public class EquipoPayload implements Serializable {

    @Serial
    private static final long serialVersionUID = -1161757771132199588L;

    @JsonProperty("nombre")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre = null;

    @JsonProperty("liga")
    @NotBlank(message = "La liga es obligatoria")
    private String liga = null;

    @JsonProperty("pais")
    @NotBlank(message = "El país es obligatorio")
    private String pais = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipoPayload that)) return false;
        return getNombre().equals(that.getNombre()) && getLiga().equals(that.getLiga()) && getPais().equals(that.getPais());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getLiga(), getPais());
    }
}
