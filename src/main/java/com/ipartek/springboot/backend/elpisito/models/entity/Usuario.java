package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	@Serial
	private static final long serialVersionUID = 6501048567006247146L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //IDENTITY es el incremental para MySQL
	@Column
	private Long id;
	
	
	@Column
	private String user;
	
	
	@Column
	private String password;
	
	
	@Column
	private String passopen; //El password sin hashear
	
	
	@Column
	private String email;
	
	
	@Column
	private String rol="ROLE_USER";
	
	
	@Column
	private Integer activo=1;




}
