package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
@Table(name="imagenes_banner")
public class ImagenBanner implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -4200639114845575130L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String nombre; //64748844899490400404.jpg
	

	
	@JsonIgnore
	@OneToOne(mappedBy = "imagen")
    private BannerHorizontal banner;
	
	
	
	
	

}
