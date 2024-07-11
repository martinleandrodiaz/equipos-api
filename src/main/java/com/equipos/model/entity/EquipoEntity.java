package com.equipos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "equipos")
public class EquipoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5009531643661840582L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "nombre")
    private String nombre;

    @Column(nullable = false, name = "liga")
    private String liga;

    @Column(nullable = false, name = "pais")
    private String pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipoEntity that)) return false;
        return getId().equals(that.getId()) && getNombre().equals(that.getNombre()) && getLiga().equals(that.getLiga()) && getPais().equals(that.getPais());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getLiga(), getPais());
    }
}

