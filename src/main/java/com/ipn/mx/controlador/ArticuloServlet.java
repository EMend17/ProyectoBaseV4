/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.ArticuloDAO;
import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.ArticuloDTO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author EMend17
 */
@WebServlet(name = "ArticuloServlet", value = "/ArticuloServlet")
public class ArticuloServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        if (accion.equals("listaDeArticulos")) {
            listadoArticulo(request, response);
        } else {
            if (accion.equals("nuevo")) {
                crearArticulo(request, response);
            } else {
                if (accion.equals("actualizar")) {
                    actualizarArticulo(request, response);
                } else {
                    if (accion.equals("eliminar")) {
                        eliminarArticulo(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarArticulo(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarArticulo(request, response);
                            } else {
                                if (accion.equals("verReporte")) {
                                    mostrarReporte(request, response);
                                } else {
                                    if (accion.equals("graficar")) {
                                        mostrarGrafica(request, response);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ArticuloServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ArticuloServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listadoArticulo(HttpServletRequest request, HttpServletResponse response) {
        ArticuloDAO dao = new ArticuloDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listado", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/articulo/listaDeArticulos.jsp");
            rd.forward(request, response);
        } catch (SQLException | jakarta.servlet.ServletException | IOException ex) {
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearArticulo(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/articulo/articuloForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) {
        ArticuloDAO dao = new ArticuloDAO();
        ArticuloDTO dto = new ArticuloDTO();
        dto.getEntidad().setIdArticulo(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            dao.delete(dto);
            listadoArticulo(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarArticulo(HttpServletRequest request, HttpServletResponse response) {
        ArticuloDAO dao = new ArticuloDAO();
        ArticuloDTO dto = new ArticuloDTO();
        dto.getEntidad().setIdArticulo(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("dto", dto);
            RequestDispatcher rd = request.getRequestDispatcher("/articulo/articuloForm.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarArticulo(HttpServletRequest request, HttpServletResponse response) {
        ArticuloDAO dao = new ArticuloDAO();
        ArticuloDTO dto = new ArticuloDTO();

        if (request.getAttribute("dto") == null) {

            dto.getEntidad().setNombreArticulo(request.getParameter("txtNombre"));
            dto.getEntidad().setDescripcionArticulo(request.getParameter("txtDescripcion"));
            dto.getEntidad().setPrecioArticulo(Integer.parseInt(request.getParameter("txtPrecio")));
            dto.getEntidad().setExistenciaArticulo(Integer.parseInt(request.getParameter("txtExistencia")));
            dto.getEntidad().setStackMinArticulo(Integer.parseInt(request.getParameter("txtStackMin")));
            dto.getEntidad().setStackMaxArticulo(Integer.parseInt(request.getParameter("txtStackMax")));
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtIdCat")));
            try {
                dao.create(dto);
                request.setAttribute("mensajeOK", "El registro se almaceno correctamente");
                listadoArticulo(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dto.getEntidad().setNombreArticulo(request.getParameter("txtNombre"));
            dto.getEntidad().setDescripcionArticulo(request.getParameter("txtDescripcion"));
            dto.getEntidad().setPrecioArticulo(Integer.parseInt(request.getParameter("txtPrecio")));
            dto.getEntidad().setExistenciaArticulo(Integer.parseInt(request.getParameter("txtExistencia")));
            dto.getEntidad().setStackMinArticulo(Integer.parseInt(request.getParameter("txtStackMin")));
            dto.getEntidad().setStackMaxArticulo(Integer.parseInt(request.getParameter("txtStackMax")));
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtIdCat")));
            dto.getEntidad().setIdArticulo(Integer.parseInt(request.getParameter("txtId")));

            try {
                dao.update(dto);
                request.removeAttribute("dto");
                listadoArticulo(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostrarArticulo(HttpServletRequest request, HttpServletResponse response) {
        ArticuloDAO dao = new ArticuloDAO();
        ArticuloDTO dto = new ArticuloDTO();
        dto.getEntidad().setIdArticulo(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("dto", dto);
            RequestDispatcher rd = request.getRequestDispatcher("/articulo/verArticulo.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarReporte(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mostrarGrafica(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
