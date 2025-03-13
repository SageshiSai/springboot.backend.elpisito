package com.ipartek.springboot.backend.elpisito.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode
@Table(name = "poblaciones")
public class Poblacion implements Serializable {
    
    @Serial
	private static final long serialVersionUID = 1367413767728793728L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NonNull
    private String nombre;

    @Column
    private Integer activo = 1;

    @ManyToOne //muchas poblaciones pertenecen a una provincia,
    @JoinColumn(name = "provincia")
    private Provincia provincia; //Este es el mapped by de el @OneToMany


    public Integer getActivo() {
        return activo;
    }
}
