/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.DatosGraficaDTO;
import java.sql.CallableStatement;
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
 * @author darkdestiny
 */
public class CategoriaDAO {

    private static final String SQL_INSERT = " INSERT INTO Categoria (idCategoria, nombreCategoria, nombreCategoria) VALUES (?, ?, ?);  ";
    private static final String SQL_UPDATE = " UPDATE Categoria   \n" +
                                            " SET idCategoria = ?,  \n" +
                                            " nombreCategoria = ?,  \n" +
                                            " descripcionCategoria = ?   \n" +
                                            " WHERE\n" +
                                            " idCategoria = ?;";
    private static final String SQL_DELETE = "DELETE FROM Categoria WHERE idCategoria = ? ";
    private static final String SQL_READ = "SELECT * FROM Categoria WHERE idCategoria = ?";
    private static final String SQL_READ_ALL = "SELECT * FROM Categoria;";
    private static final String SQL_GRAFICAR = "{call spGraficar()}";
    
    

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

    public void create(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreCategoria());
            ps.setString(2, dto.getEntidad().getDescripcionCategoria());
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

    public void update(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement cs = null;
        try {
            cs = conexion.prepareStatement(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.setInt(3, dto.getEntidad().getIdCategoria());
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

    public void delete(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement cs = null;
        try {
            cs = conexion.prepareStatement(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
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

    public CategoriaDTO read(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement cs = null;
        ResultSet rs = null;
        try {
            cs = conexion.prepareStatement(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (CategoriaDTO) resultados.get(0);
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

    public List graficar() throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        List Lista = new ArrayList();

        try {
            cs = conexion.prepareCall(SQL_GRAFICAR);
            rs = cs.executeQuery();
            while(rs.next()){
                DatosGraficaDTO datos =new DatosGraficaDTO();
                datos.setNombre(rs.getString("nombreCategoria"));
                datos.setCantidad(rs.getInt("cantidad"));
                Lista.add(datos);
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
        return Lista;

    }

    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while (rs.next()) {
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }

    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setNombreCategoria("Línea Blanca");
        dto.getEntidad().setDescripcionCategoria("Artículos de Línea Blanca");
//        dto.getEntidad().setNombreCategoria("Computación");
//        dto.getEntidad().setDescripcionCategoria("Artículos de Computación");
        //dto.getEntidad().setIdCategoria(3);
        try {
            dao.create(dto);
            //dao.update(dto);
            //System.out.println(dao.readAll());
            System.out.println(dao.read(dto));
            //dao.delete(dto);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
