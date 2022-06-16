/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author EMend17
 */
@Data
@NoArgsConstructor
public class Articulo implements Serializable{
    private int idArticulo;
    private String nombreArticulo;
    private String descripcionArticulo;
    private double precioArticulo;
    private int existenciaArticulo;
    private int stackMinArticulo;
    private int stackMaxArticulo;
    private int idCategoria;
    
    
     }
     
     
     
     
