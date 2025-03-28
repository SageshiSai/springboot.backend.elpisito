package com.ipartek.springboot.backend.elpisito.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="imagenes_carousel")
public class ImagenCarousel implements Serializable {

    @Serial
    private static final long serialVersionUID = -4310031298739510204L;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nombre; //64748844899490400404.jpg

    @Column
    private Integer activo=1;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="banner_carousel")
    private BannerCarousel banner;//Este es el mapped by del @OneToOne de la clase BannerCarousel


}
