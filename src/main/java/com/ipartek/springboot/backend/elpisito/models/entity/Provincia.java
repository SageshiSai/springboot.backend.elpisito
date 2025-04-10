package com.ipartek.springboot.backend.elpisito.models.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @NonNull
    private String nombre; //BIZKAIA, MADRID ....

    @Column
    private Integer activo = 1;

    @JsonIgnore       //@JsonManagedReference
    @OneToMany(mappedBy = "provincia") //Una Provincia puede tener muchas Poblaciones
    private Set<Poblacion> poblaciones;
}
