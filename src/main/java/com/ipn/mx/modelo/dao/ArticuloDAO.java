/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Articulo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EMend17
 */
public class ArticuloDAO {

    private static final String SQL_INSERT = "{call spInsertarA( ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String SQL_UPDATE = "{call spActualizarA (?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String SQL_DELETE = "{call spEliminara(?)}";
    private static final String SQL_READ = "{call spSeleccionarUnA(?)}";
    private static final String SQL_READ_ALL = "{call spSeleccionarTodoA()}";

    private Connection conexion;

    private void obtenerConexion() {

        //obtener conexion
        String usuario = "root";
        String clave = "admin";
        String url = "jdbc:mysql://localhost:3306/ProyectoBase4?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        //String url = "jdbc:mysql://localhost:3306/EscuelaWeb?
        //serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true&
        //useSSL=false";

        String driverBD = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        
        
        
        
        
        
        
        
    }
    
    
}
