/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author BRASHAN
 */
@WebServlet(name = "Confirmar", urlPatterns = {"/confirmar"})
public class Confirmar extends HttpServlet {
    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Confirmar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Confirmar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    String nombre = request.getParameter("nombre");
    String autor = request.getParameter("autor");
    String duracion = request.getParameter("duracion");
    String fecha_creacion = request.getParameter("fecha_creacion");
    String calificacion = request.getParameter("calificacion");


    Connection conn = null;
    PreparedStatement ps = null;

    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        try {
            ConnectionBD conexion = new ConnectionBD();
            conn = conexion.getConnectionBD();

            if (conn != null) {
                System.out.println("Conexión establecida exitosamente");
            } else {
                System.out.println("Error al establecer la conexión");
                out.println("<p style='color:red;'>Error al conectar con la base de datos.</p>");
                return;
            }

            String sql = "INSERT INTO canciones (nombre, autor, duracion, fecha_creacion, calificacion) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, autor);
            ps.setString(3, duracion);
            ps.setString(4, fecha_creacion);
            ps.setString(5, calificacion);

          
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Correcto se esta redirigiendo a borar");
               request.getRequestDispatcher("/jsp/borrar.jsp").forward(request,response);
            } else {
                System.out.println("No se insertó ninguna fila");
                out.println("<p style='color:red;'>Error al insertar la canción.</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Ocurrió un error: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
                System.out.println("Conexión cerrada");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p style='color:red;'>Ocurrió un error al cerrar la conexión: " + e.getMessage() + "</p>");
            }
        }
    }
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

}
