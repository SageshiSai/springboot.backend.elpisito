package com.ipartek.springboot.backend.elpisito.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tipos")
public class Tipo implements Serializable {

    @Serial
	private static final long serialVersionUID = -2405395549485641981L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre; //chalet, piso, casa

    @Column
    private Integer activo;
}
