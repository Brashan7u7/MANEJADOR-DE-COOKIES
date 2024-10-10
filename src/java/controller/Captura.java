package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "Captura", urlPatterns = {"/captura"})
public class Captura extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Captura</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Captura at " + request.getContextPath() + "</h1>");
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
        
        String encodedNombre = URLEncoder.encode(nombre, "UTF-8");
        String encodedAutor = URLEncoder.encode(autor, "UTF-8");
        String encodedDuracion = URLEncoder.encode(duracion, "UTF-8");
        String encodedFechaCreacion = URLEncoder.encode(fecha_creacion, "UTF-8");
        String encodedCalificacion = URLEncoder.encode(calificacion, "UTF-8");
        
        
        Cookie nombreCookie = new Cookie("nombre", encodedNombre);
        Cookie autorCookie = new Cookie("autor", encodedAutor);
        Cookie duracionCookie = new Cookie("duracion", encodedDuracion);
        Cookie fecha_creacionCookie = new Cookie("fecha_creacion", encodedFechaCreacion);
        Cookie calificacionCookie = new Cookie("calificacion", encodedCalificacion);
        
        nombreCookie.setMaxAge(60*5);
        autorCookie.setMaxAge(60*5);
        duracionCookie.setMaxAge(60*5);
        fecha_creacionCookie.setMaxAge(60*5);
        calificacionCookie.setMaxAge(60*5);
        
        
        response.addCookie(nombreCookie);
        response.addCookie(autorCookie);
        response.addCookie(duracionCookie);
        response.addCookie(fecha_creacionCookie);
        response.addCookie(calificacionCookie);
        
        request.getRequestDispatcher("/jsp/mostrar.jsp").forward(request,response);
        
        
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
