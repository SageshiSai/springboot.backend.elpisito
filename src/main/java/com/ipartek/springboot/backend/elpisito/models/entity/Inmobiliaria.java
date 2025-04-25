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
import jakarta.persistence.OneToMany;
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
@Table(name="inmobiliarias")
public class Inmobiliaria implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 6823088879491405754L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	
	@Column(unique = true)
	private String nombre;
	
	
	@Column
	private String telefono;
	
	@Column
	private String representante;
	
	
	@Column
	private Integer activo=1;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen", referencedColumnName = "id")
    private ImagenLogo imagen;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="inmobiliaria", cascade = CascadeType.ALL)
	private Set<Inmueble> inmuebles;
	
	

}
