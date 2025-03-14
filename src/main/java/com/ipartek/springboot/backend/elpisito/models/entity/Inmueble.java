package com.ipartek.springboot.backend.elpisito.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Builder
@Table(name = "inmuebles")
public class Inmueble implements Serializable {

    @Serial
	private static final long serialVersionUID = -2246669607703879790L;

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


    @ManyToOne
    @JoinColumn(name = "tipo")
    private Tipo tipo;

    @JsonManagedReference
    @OneToMany(mappedBy = "inmueble", cascade = CascadeType.ALL)
    private Set<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;

    @JsonManagedReference
    @OneToMany(mappedBy = "inmueble", cascade = CascadeType.ALL)
    private Set<Archivo> archivos;
}
