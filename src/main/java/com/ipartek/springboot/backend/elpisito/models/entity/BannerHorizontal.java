package com.ipartek.springboot.backend.elpisito.models.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name="banners_horizontales")
public class BannerHorizontal implements Serializable {

	@Serial
	private static final long serialVersionUID = -2539342254321679493L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Integer activo=1;

    @Column(name="alt_imagen")
    private String altImagen;

    @Column
    private String titular;

    @Column
    private String claim;

    @Column
    private String link;

    @Column(name = "texto_link")
    private String textoLink;

    @JsonManagedReference
    @OneToOne(mappedBy = "banner")
    private ImagenBanner imagen;
}
