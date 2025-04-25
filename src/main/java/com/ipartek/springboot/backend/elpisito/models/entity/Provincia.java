package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="provincias")
public class Provincia implements Serializable{
	
	@Serial
	private static final long serialVersionUID = -5858367880341896796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(unique = true)
	private String nombre; //BIZKAIA,MADRID,BARCELONA,SEVILLA...
	
	
	@Column
	private Integer activo=1;
	
	@JsonIgnore       //@JsonManagedReference
	@OneToMany(mappedBy = "provincia") //Una Provincia puede tener muchas Poblaciones
	private Set<Poblacion> poblaciones;
	
	
	
	
	
	

}
