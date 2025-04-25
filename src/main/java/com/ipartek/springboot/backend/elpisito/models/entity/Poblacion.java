package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name="poblaciones")
public class Poblacion implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -5763929061016836220L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(unique = true)
	private String nombre; //Algorta, Móstoles...
	
	@Column
	private Integer activo = 1;
	
	//La tabla del JoinColumn es la que va a tener la anotación @ManyToOne
	@ManyToOne //Muchas Poblaciones pueden pertenecer a una Provincia
	@JoinColumn(name="provincia")
	private Provincia provincia; //Este es el MappedBy del @OneToMany de la clase Provincia
	
	@JsonIgnore
	@OneToMany(mappedBy="poblacion", cascade = CascadeType.ALL)
	private Set<Inmueble> inmuebles;
	

}
