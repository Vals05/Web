package com.tienda.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L; // Cambiado de 'serialVersion' a 'serialVersionUID'
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    
    private Long idProducto;
    
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    
    @ManyToOne //Efectivamente la asociación de muchos a uno 
    @JoinColumn (name="id_categoria")  //Indicar el atributo en este caso de la tabla
    private Categoria categoria;
    
}
