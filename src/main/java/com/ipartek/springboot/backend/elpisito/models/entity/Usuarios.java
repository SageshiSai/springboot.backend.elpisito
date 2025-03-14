package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name="usuarios")
public class Usuarios implements Serializable{
	
	@Serial
	private static final long serialVersionUID = 1196793780913809106L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String user;
	
	@Column 
	private String  password;
	 
	@Column(name="passopen")
	private String  passOpen; //El password sin hashear

	@Column
	private Integer activo;

	
}
