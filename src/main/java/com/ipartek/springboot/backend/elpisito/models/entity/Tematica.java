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
@Table(name="tematicas")
public class Tematica implements Serializable {

    @Serial
    private static final long serialVersionUID = -2517325604129435271L;


    //Esta clase sirve para contener los tipos de temática que puede 
    //tener los banners del carousel home para que podamos administrarlos
    //desde el panel de administración de cliente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @Column
    private String tematica;//primavera,otoño,navidad,general...

    @Column
    private Integer activo=1;


    @JsonIgnore
    @OneToMany(mappedBy = "tematica") //Una Provincia puede tener muchas Poblaciones
    private Set<BannerCarousel> bannersCarousel;


}
