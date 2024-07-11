package com.equipos.service.config;

import com.equipos.model.dto.EquipoDTO;
import com.equipos.model.entity.EquipoEntity;
import com.equipos.model.payload.EquipoPayload;

public class EquipoConfig {

    private static final Long ID = 1L;
    private static final String LIGA = "Liga Argentina";
    private static final String NOMBRE = "Talleres";

    public static EquipoEntity getEquipoEntity() {
        EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setId(ID);
        equipoEntity.setLiga(LIGA);
        equipoEntity.setNombre(NOMBRE);

        return equipoEntity;
    }

    public static EquipoDTO getEquipoDTO() {
        EquipoDTO equipoDTO = new EquipoDTO();
        equipoDTO.setId(ID);
        equipoDTO.setLiga(LIGA);
        equipoDTO.setNombre(NOMBRE);

        return equipoDTO;
    }

    public static EquipoPayload getEquipoPayload() {
        EquipoPayload equipoPayload = new EquipoPayload();
        equipoPayload.setLiga(LIGA);
        equipoPayload.setNombre(NOMBRE);

        return equipoPayload;
    }
}
