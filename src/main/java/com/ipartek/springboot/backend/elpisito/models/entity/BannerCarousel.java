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
@Table(name="banners_carousel")
public class BannerCarousel implements Serializable {

    @Serial
    private static final long serialVersionUID = 4325969584571100023L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Integer activo = 1;

    @Column(name= "alt_imagen")
    private String altImagen;

    @Column
    private String titular;

    @Column
    private String claim;

    @Column
    private String tematica;//primavera,otoño,verano,invierno,navidad

    @OneToOne(mappedBy = "banner")
    private ImagenCarousel imagen;




}
