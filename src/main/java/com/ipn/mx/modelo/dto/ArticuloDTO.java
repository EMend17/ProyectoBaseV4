/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Articulo;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author EMend17
 */
@Data
public class ArticuloDTO implements Serializable{
    private Articulo entidad;
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idArticulo").append(getEntidad().getIdArticulo()).append("\n");
        sb.append("nombreArticulo").append(getEntidad().getNombreArticulo()).append("\n");
        sb.append("descripcionArticulo").append(getEntidad().getDescripcionArticulo()).append("\n");
        sb.append("precioArticulo").append(getEntidad().getPrecioArticulo()).append("\n");
        sb.append("existenciaArticulo").append(getEntidad().getExistenciaArticulo()).append("\n");
        sb.append("stackMinArticulo").append(getEntidad().getStackMinArticulo()).append("\n");
        sb.append("stackMaxArticulo").append(getEntidad().getStackMaxArticulo()).append("\n");
        sb.append("idCategoria").append(getEntidad().getIdCategoria()).append("\n");
        return sb.toString();
    }
}
