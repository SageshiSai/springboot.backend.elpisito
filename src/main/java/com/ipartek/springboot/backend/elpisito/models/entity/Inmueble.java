package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="inmuebles")
public class Inmueble implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 9168386848968642920L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String via; //CALLE,PLAZA,CARRETERA,AVENIDA...
	
	
	@Column
	private String claim; //Maravilloso piso!!!!
	
	@Column(name="nombre_via")
	private String nombreVia;
		
	@Column
	private String numero;
	
	@Column
	private String planta;
	
	
	@Column
	private String puerta; //A,B,H...
	
	
	@Column
	private String apertura; //EXTERIOR, INTERIOR...
	
	
	@Column
	private String orientacion; //NORTE,SUR...
	
	
	@Column
	private String cp;
	
	
	
	@Column
	private String operacion; //VENTA, ALQUILER, TRASPASO...
	
	
	@Column(name="superficie_util")
	private Double superficieUtil;
	
	
	
	@Column(name="superficie_construida")
	private Double superficieConstruida;
	
	
	@Column
	private Double precio;
	
	
	@Column(name="numero_habitaciones")
	private String numeroHabitaciones;
	
	@Column(name="numero_banhos")
	private String numeroBanhos;
	
	
	@Column(length = 3500)
	private String descripcion; //Una amplia descripción del inmueble
	
	
	@Column(name="tipo_calefaccion")
	private String tipoCalefaccion; //ELÉCTRICA, GAS, SIN CALEFACCIÓN...
	
	
	@Column
	private Integer amueblado; //1 amueblado, 0 no amueblado
	
	
	@Column(name="numero_balcones")
	private String numeroBalcones;
	
	
	@Column(name="plazas_garaje")
	private String plazasGaraje;
	
	
	@Column
	private Integer piscina;
	
	
	@Column
	private Integer trastero;
	
	
	@Column
	private Integer ascensor;
	
	
	@Column
	private Integer jardin;
	
	
	@Column
	private Integer tendedero;
	
	
	@Column
	private Integer portada=0;
	
	
	@Column
	private Integer activo=1;
	
	
	@Column
	private Integer oportunidad = 0; //Sirve para sacar en cliente un banercito de oportunidad
	
	
	@ManyToOne
	@JoinColumn(name="tipo")
	private Tipo tipo; //Este es el mappedBy  de @OneToMany de la clase Tipo
	
	
	@OneToMany(mappedBy = "inmueble")
	private Set<Imagen> imagenes;
	
	@OneToMany(mappedBy = "inmueble")
	private Set<Archivo> archivos;
	
	
	@ManyToOne
	@JoinColumn(name= "poblacion")
	private Poblacion poblacion;
	
	
	@ManyToOne
	@JoinColumn(name= "inmobiliaria")
	private Inmobiliaria inmobiliaria;
	
	
	

}
