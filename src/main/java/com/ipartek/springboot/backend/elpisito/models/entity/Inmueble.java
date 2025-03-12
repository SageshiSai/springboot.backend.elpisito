package com.ipartek.springboot.backend.elpisito.models.entity;

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
@Table(name = "inmuebles")
public class Inmueble implements Serializable {

    @Serial
	private static final long serialVersionUID = -2246669607703879790L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String via; //Calle, plaza, carretera, avenida.....

    @Column
    private String claim; //Maravilloso Pisoo!!

    @Column(name = "nombre_via")
    private String nombreVia;

    @Column
    private String numero;

    @Column
    private String planta;

    @Column
    private String puerta; //Puerta a, b ... , h

    @Column
    private String apertura; //Exterior, Interior, agujero....

    @Column
    private String orientacion; //NORTE, SUR....

    @Column
    private String cp;

    @Column
    private String operacion; //Venta, alquiler, traspaso

    @Column(name = "superficie_util")
    private Double superficieUtil;

    @Column(name = "superficie_construida")
    private Double superficieConstruida;

    @Column
    private Double precio;

    @Column(name = "numero_habitaciones")
    private String numeroHabitaciones;

    @Column(name = "numero_banhos")
    private String numeroBanhos;

    @Column(length = 3500)
    private String decripcion; //Una amplia descripcion del inmueble

    @Column(name = "tipo_calefaccion")
    private String tipoCalefaccion; //ELECTRICA, GAS, SIN CALEFACCION...

    @Column
    private Integer amueblado;

    @Column(name = "numero_balcones")
    private Integer numeroBalcones;

    @Column(name = "plazas_garaje")
    private String plazasGaraje;

    @Column
    private Integer priscina;

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

    public Integer getActivo() {
        return activo;
    }

    @ManyToOne
    @JoinColumn(name = "tipo")
    private Tipo tipo;

    @OneToMany(mappedBy = "inmueble", cascade = CascadeType.ALL)
    private Set<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;

    @OneToMany(mappedBy = "inmueble", cascade = CascadeType.ALL)
    private Set<Archivo> archivos;
}
