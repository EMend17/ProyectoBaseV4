/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ArticuloDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EMend17
 */
public class ArticuloDAO {

    private static final String SQL_INSERT = "INSERT INTO Articulo (idArticulo, nombreArticulo, descripcionArticulo, precioArticulo, existenciaArticulo, stackMinArticulo, stackMaxArticulo, idCategoria) "
                                              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE Articulo   \n" +
                                            " SET idArticulo = ?,  \n" +
                                            " nombreArticulo = ?,  \n" +
                                            " descripcionArticulo = ?,  \n" +
                                            " precioArticulo = ?,  \n" +
                                            " existenciaArticulo = ?,  \n" +
                                            " stackMinArticulo = ?,  \n" +
                                            " stackMaxArticulo = ?,  \n" +
                                            " idCategoria = ?   \n" +
                                            " WHERE\n" +
                                            " idArticulo = ?;";
    private static final String SQL_DELETE = "DELETE FROM Articulo WHERE idArticulo = ? ";
    private static final String SQL_READ = " SELECT * FROM Articulo WHERE idArticulo = ?";
    private static final String SQL_READ_ALL = "SELECT * FROM Articulo;";

    private Connection conexion;

     private void obtenerConexion() {
        //obtener conexion
        String usuario = "wtacdasmbsblsi";
        String clave = "d20f03557f1e0f3feeea26b4bcf13d2d625434d9d5712948122b1fe9fb60e138";
        String url = "jdbc:postgresql://ec2-52-73-184-24.compute-1.amazonaws.com:5432/d5g17l1sqiogql";
        //String url = "jdbc:mysql://localhost:3306/EscuelaWeb?
        //serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true&
        //useSSL=false";

        String driverBD = "org.postgresql.Driver";

        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     public void create(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreArticulo());
            ps.setString(2, dto.getEntidad().getDescripcionArticulo());
            ps.setInt(3, (int) dto.getEntidad().getPrecioArticulo());
            ps.setInt(4, dto.getEntidad().getExistenciaArticulo());
            ps.setInt(5, dto.getEntidad().getStackMinArticulo());
            ps.setInt(6, dto.getEntidad().getStackMaxArticulo());
            ps.setInt(7, dto.getEntidad().getIdCategoria());           
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
        
     public void update(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
             ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreArticulo());
            ps.setString(2, dto.getEntidad().getDescripcionArticulo());
            ps.setInt(3, (int) dto.getEntidad().getPrecioArticulo());
            ps.setInt(4, dto.getEntidad().getExistenciaArticulo());
            ps.setInt(5, dto.getEntidad().getStackMinArticulo());
            ps.setInt(6, dto.getEntidad().getStackMaxArticulo());
            ps.setInt(7, dto.getEntidad().getIdCategoria());    
            ps.setInt(8, dto.getEntidad().getIdArticulo());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }    
        
        public void delete(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement cs = null;
        try {
            cs = conexion.prepareStatement(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdArticulo());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
        
        public ArticuloDTO read(ArticuloDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            cs = conexion.prepareStatement(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdArticulo());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (ArticuloDTO) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
        
             public List readAll() throws SQLException {
        obtenerConexion();
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            cs = conexion.prepareStatement(SQL_READ_ALL);
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
                
        
        
        private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while (rs.next()) {
            ArticuloDTO dto = new ArticuloDTO();
            dto.getEntidad().setIdArticulo(rs.getInt("idArticulo"));
            dto.getEntidad().setNombreArticulo(rs.getString("nombreArticulo"));
            dto.getEntidad().setDescripcionArticulo(rs.getString("descripcionArticulo"));
            resultados.add(dto);
        }
        return resultados;
    }
        
    }
    
    

