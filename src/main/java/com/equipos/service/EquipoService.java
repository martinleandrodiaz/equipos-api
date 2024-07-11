package com.equipos.service;

import com.equipos.exception.NotFoundException;
import com.equipos.model.dto.EquipoDTO;
import com.equipos.model.entity.EquipoEntity;
import com.equipos.model.payload.EquipoPayload;
import com.equipos.repository.EquipoRepository;
import com.equipos.service.mapper.EquipoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    public static final String EQUIPO_NOT_FOUND = "Equipo no encontrado";

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;

    public EquipoService(EquipoRepository equipoRepository, EquipoMapper equipoMapper) {
        this.equipoRepository = equipoRepository;
        this.equipoMapper = equipoMapper;
    }

    public List<EquipoDTO> getAll() {
        return equipoRepository.findAll().stream().map(equipoMapper::getEquipoDtoFromEquipoEntity).toList();
    }

    public EquipoDTO getById(Long id) {
        Optional<EquipoEntity> equipoEntity = equipoRepository.findById(id);

        if(equipoEntity.isEmpty()) {
            throw new NotFoundException(EQUIPO_NOT_FOUND);
        }

        return equipoMapper.getEquipoDtoFromEquipoEntity(equipoEntity.get());
    }

    public List<EquipoDTO> findByNombre(String nombre) {
        return equipoRepository.findByNombreContaining(nombre).stream().map(equipoMapper::getEquipoDtoFromEquipoEntity).toList();
    }

    public EquipoDTO persistEquipo(EquipoPayload equipoPayload) {
        EquipoEntity equipoEntity = equipoRepository.saveAndFlush(equipoMapper.getEquipoEntityFromEquipoPayload(null, equipoPayload));
        return equipoMapper.getEquipoDtoFromEquipoEntity(equipoEntity);
    }

    public EquipoDTO updateEquipo(Long id, EquipoPayload equipoPayload) {
        if(!equipoRepository.existsById(id)) {
            throw new NotFoundException(EQUIPO_NOT_FOUND);
        }
        EquipoEntity equipoEntity = equipoMapper.getEquipoEntityFromEquipoPayload(id, equipoPayload);
        return equipoMapper.getEquipoDtoFromEquipoEntity(equipoRepository.saveAndFlush(equipoEntity));
    }

    public void deleteEquipo(Long id) {
        if(!equipoRepository.existsById(id)) {
            throw new NotFoundException(EQUIPO_NOT_FOUND);
        }
        equipoRepository.deleteById(id);
    }
}
