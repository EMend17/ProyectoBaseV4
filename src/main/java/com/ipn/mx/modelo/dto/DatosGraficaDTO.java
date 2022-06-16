/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import java.io.Serializable;

/**
 *
 * @author EMend17
 */
public class DatosGraficaDTO implements Serializable {

    private int cantidad;
    private String nombre;

    public DatosGraficaDTO(int cantidad, String nombre) {
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public DatosGraficaDTO() {
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre").append(this.getNombre()).append("\n");
        sb.append("Cantidad").append(this.getCantidad()).append("\n");
        return sb.toString();
    }

    
    
}
