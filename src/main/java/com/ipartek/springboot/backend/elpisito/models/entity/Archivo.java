package com.ipartek.springboot.backend.elpisito.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "archivos")
public class Archivo implements Serializable {

    
    @Serial
	private static final long serialVersionUID = 8121319919778040446L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer active=1;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "inmueble")
    private Inmueble inmueble;
}
