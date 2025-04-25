package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	
	@ManyToOne //Muchas BannersCarousel pueden pertenecer a una Tematica
	@JoinColumn(name="tematica")
	private Tematica tematica;//Este es el MappedBy del @OneToMany de la clase Tematica
	

	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen", referencedColumnName = "id")
    private ImagenCarousel imagen;
	
	
	

}
