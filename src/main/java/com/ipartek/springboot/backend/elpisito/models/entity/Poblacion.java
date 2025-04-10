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

    //La tabla del JoinColumn es la que va a tener la anotación @ManyToOne
    @ManyToOne //Muchas Poblaciones pueden pertenecer a una Provincia
    @JoinColumn(name="provincia")
    private Provincia provincia; //Este es el MappedBy del @OneToMany de la clase Provincia

    @JsonIgnore
    @OneToMany(mappedBy="poblacion", cascade = CascadeType.ALL)
    private Set<Inmueble> inmuebles;

}
