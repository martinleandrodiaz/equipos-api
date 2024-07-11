package com.equipos.service;

import com.equipos.model.entity.EquipoEntity;
import com.equipos.model.payload.EquipoPayload;
import com.equipos.service.mapper.EquipoMapper;
import com.equipos.exception.ServiceException;
import com.equipos.model.dto.EquipoDTO;
import com.equipos.repository.EquipoRepository;
import com.equipos.service.config.EquipoConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private EquipoMapper equipoMapper;

    @InjectMocks
    private EquipoService equipoService;

    private EquipoEntity equipoEntity;
    private EquipoDTO equipoDTO;
    private EquipoPayload equipoPayload;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        equipoEntity = EquipoConfig.getEquipoEntity();
        equipoDTO = EquipoConfig.getEquipoDTO();
        equipoPayload = EquipoConfig.getEquipoPayload();
    }

    @Test
    void testGetAll() {

        when(equipoRepository.findAll()).thenReturn(Collections.singletonList(equipoEntity));
        when(equipoMapper.getEquipoDtoFromEquipoEntity(equipoEntity)).thenReturn(equipoDTO);

        List<EquipoDTO> result = equipoService.getAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Talleres", result.get(0).getNombre());

        verify(equipoRepository, times(1)).findAll();
        verify(equipoMapper, times(1)).getEquipoDtoFromEquipoEntity(equipoEntity);
    }

    @Test
    void testGetById() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipoEntity));
        when(equipoMapper.getEquipoDtoFromEquipoEntity(equipoEntity)).thenReturn(equipoDTO);

        EquipoDTO result = equipoService.getById(1L);
        assertNotNull(result);
        assertEquals("Talleres", result.getNombre());

        verify(equipoRepository, times(1)).findById(1L);
        verify(equipoMapper, times(1)).getEquipoDtoFromEquipoEntity(equipoEntity);
    }

    @Test
    void testGetByIdNotFound() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> equipoService.getById(1L));
        assertEquals("Equipo no encontrado", exception.getMessage());

        verify(equipoRepository, times(1)).findById(1L);
    }

    @Test
    void testPersistEquipo() {
        when(equipoRepository.saveAndFlush(any(EquipoEntity.class))).thenReturn(equipoEntity);
        when(equipoMapper.getEquipoEntityFromEquipoPayload(null, equipoPayload)).thenReturn(equipoEntity);
        when(equipoMapper.getEquipoDtoFromEquipoEntity(equipoEntity)).thenReturn(equipoDTO);

        EquipoDTO result = equipoService.persistEquipo(equipoPayload);

        assertNotNull(result);
        assertEquals("Talleres", result.getNombre());
        verify(equipoRepository, times(1)).saveAndFlush(any(EquipoEntity.class));
    }
}
