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
@Table(name="banners_horizontales")
public class BannerHorizontal implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 4113309318889223852L;

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
	private String link;
	
	@Column
	private Integer home=0;//hace referencia a si el banner aparece en la página home
	
	@Column(name= "consulta_hipotecas")
	private Integer consultaHipotecas=0;//hace referencia a si el banner aparece en la página consulta de hipotecas
	
	@Column(name= "detail_inmueble")
	private Integer detailInmueble=0;//hace referencia a si el banner aparece en la página detail inmueble
	
	@Column
	private Integer servicios=0;//hace referencia a si el banner aparece en la página servicios
	
	@Column(name= "list_inmueble_finder")
	private Integer listInmuebleFinder=0;//hace referencia a si el banner aparece en la list inmueble finder
	
	
	@Column(name= "texto_link")
	private String textoLink;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen", referencedColumnName = "id")
    private ImagenBanner imagen;
	
	

}
