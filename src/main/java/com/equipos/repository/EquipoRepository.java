package com.equipos.repository;

import com.equipos.model.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {

    List<EquipoEntity> findByNombreContaining(String nombre);
}
