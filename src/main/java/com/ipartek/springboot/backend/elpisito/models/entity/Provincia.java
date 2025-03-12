package com.ipartek.springboot.backend.elpisito.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "provincias")
public class Provincia implements Serializable {

    @Serial
	private static final long serialVersionUID = 4943012695786916263L;


	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @Column
    private String nombre; //BIZKAIA, MADRID ....

    @Column
    private Integer activo = 1;

    @JsonIgnore
    @OneToMany(mappedBy = "provincia")
    private Set<Poblacion> poblaciones;

    public Integer getActivo() {
        return activo;
    }
}
