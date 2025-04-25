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
@Table(name="tipos")
public class Tipo implements Serializable{
	
	@Serial
	private static final long serialVersionUID = 6461797043143800280L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(unique = true)
	private String nombre; //PISO, FINCA, CHALET INDIVIDUAL, CHALET ADOSADO...
	
	
	
	@Column
	private Integer activo=1;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="tipo", cascade=CascadeType.ALL)
	private Set<Inmueble> inmuebles;
	
	

}
