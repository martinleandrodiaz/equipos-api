package com.equipos.service.mapper;

import com.equipos.model.payload.EquipoPayload;
import com.equipos.model.dto.EquipoDTO;
import com.equipos.model.entity.EquipoEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EquipoMapper {

    public EquipoDTO getEquipoDtoFromEquipoEntity(EquipoEntity equipoEntity) {
        EquipoDTO equipoDTO = new EquipoDTO();
        equipoDTO.setId(equipoEntity.getId());
        equipoDTO.setNombre(equipoEntity.getNombre());
        equipoDTO.setLiga(equipoEntity.getLiga());
        equipoDTO.setPais(equipoEntity.getPais());
        return equipoDTO;
    }

    public EquipoEntity getEquipoEntityFromEquipoPayload(Long id, EquipoPayload equipoPayload) {
        EquipoEntity equipoEntity = new EquipoEntity();
        if(Objects.nonNull(id)) {
            equipoEntity.setId(id);
        }
        equipoEntity.setNombre(equipoPayload.getNombre());
        equipoEntity.setLiga(equipoPayload.getLiga());
        equipoEntity.setPais(equipoPayload.getPais());
        return equipoEntity;
    }
}
