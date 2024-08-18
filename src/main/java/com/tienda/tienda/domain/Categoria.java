package com.tienda.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L; // Cambiado de 'serialVersion' a 'serialVersionUID'

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")

    private Long idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    @OneToMany //Es una asociacion para un relacion de uno a muchos entre tablas 
    @Column(name = "id_categoria") //Es la columna con la que se hace la asociacion 
    private List<Producto> productos;

}
