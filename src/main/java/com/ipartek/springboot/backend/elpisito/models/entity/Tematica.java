package com.ipartek.springboot.backend.elpisito.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="tematicas")
public class Tematica {

    //Esta clase sirve para contener los tipos de temática que puede
    //tener los banners del carousel home para que podamos administrarlos
    //desde el panel de administración de cliente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;



}
