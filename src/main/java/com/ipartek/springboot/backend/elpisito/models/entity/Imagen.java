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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "image")
public class Imagen implements Serializable {

    @Serial
	private static final long serialVersionUID = 7025742140603943281L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column Integer activo=1;

    public Integer getActivo() {
        return activo;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "inmueble")
    private Inmueble inmueble;
}
