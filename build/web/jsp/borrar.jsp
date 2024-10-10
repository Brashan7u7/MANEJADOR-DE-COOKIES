<%@ page import="java.sql.*" %>
<%@ page import="configuration.ConnectionBD" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Borrar Datos</title>
    </head>
    <body>
        <h1>Información de la Canción</h1>
        
        <%
            Cookie[] cookies = request.getCookies();
            String nombre = "";
            boolean hasCookies = false;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("nombre")) {
                        nombre = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        hasCookies = true;
                        break;
                    }
                }
            }

            if (hasCookies) {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    ConnectionBD conexion = new ConnectionBD();
                    conn = conexion.getConnectionBD();
                    String sql = "SELECT * FROM canciones WHERE nombre = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, nombre);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        out.println("<p><strong>Nombre de la canción:</strong> " + rs.getString("nombre") + "</p>");
                        out.println("<p><strong>Autor:</strong> " + rs.getString("autor") + "</p>");
                        out.println("<p><strong>Duración:</strong> " + rs.getString("duracion") + "</p>");
                        out.println("<p><strong>Fecha de creación:</strong> " + rs.getString("fecha_creacion") + "</p>");
                        out.println("<p><strong>Calificación:</strong> " + rs.getString("calificacion") + "</p>");
                    } else {
                        out.println("<p style='color:red;'>No se encontraron canciones con ese nombre.</p>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<p style='color:red;'>Error al consultar la base de datos.</p>");
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (ps != null) ps.close();
                        if (conn != null) conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                out.println("<p style='color:red;'>No hay cookies vigentes.</p>");
            }
        %>
        
        <form action="<%= request.getContextPath() %>/limpiar" method="get">
            <input type="submit" value="Borrar Cookie">
        </form>
    </body>
</html>
