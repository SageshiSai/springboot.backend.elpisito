package com.ipartek.springboot.backend.elpisito.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tipos")
public class Tipo implements Serializable {

    @Serial
	private static final long serialVersionUID = -2405395549485641981L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nombre; //PISO, FINCA, CHALET INDIVIDUAL, CHALET ADOSADO...



    @Column
    private Integer activo=1;


    @JsonIgnore
    @OneToMany(mappedBy="tipo", cascade=CascadeType.ALL)
    private Set<Inmueble> inmuebles;


}
