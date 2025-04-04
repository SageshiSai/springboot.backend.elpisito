package com.ipartek.springboot.backend.elpisito.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@EqualsAndHashCode
@Table(name="imagen_banner")
public class ImagenBanner implements Serializable {

    @Serial
	private static final long serialVersionUID = -1714028844279231043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nombre; //64748844899490400404.jpg


    @Column
    private Integer activo=1;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="banner")
    private BannerHorizontal banner;

}
