package com.tallerdesarollo.Api.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Entity(name = "tb_categoria")
//@Table
public class Categoria {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column
    private int idCat;

    //@Column(name = "nomprecatego")
    private String nombreCat;

    //@Column(name = "nivel")
    private int nivel;
}
