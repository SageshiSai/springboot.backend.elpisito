package com.ipartek.springboot.backend.elpisito.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;

public class Imagen implements Serializable {

    @Serial
	private static final long serialVersionUID = 7025742140603943281L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column Integer activo;

    public Integer getActivo() {
        return activo;
    }
}
