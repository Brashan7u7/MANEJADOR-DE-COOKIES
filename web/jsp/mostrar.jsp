<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Datos</title>
    </head>
    <body>
        <h1>Datos Ingresados</h1>
        
        <%
            Cookie[] cookies = request.getCookies();
            String nombre = "";
            String autor = "";
            String duracion = "";
            String fecha_creacion = "";
            String calificacion = "";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    switch (cookie.getName()) {
                        case "nombre":
                            nombre = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            break;
                        case "autor":
                            autor = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            break;
                        case "duracion":
                            duracion = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            break;
                        case "fecha_creacion":
                            fecha_creacion = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            break;
                        case "calificacion":
                            calificacion = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            break;
                    }
                }
            }
        %>
        
        <p><strong>Nombre de la canción:</strong> <%= nombre %></p>
        <p><strong>Autor:</strong> <%= autor %></p>
        <p><strong>Duración:</strong> <%= duracion %></p>
        <p><strong>Fecha de creación:</strong> <%= fecha_creacion %></p>
        <p><strong>Calificación:</strong> <%= calificacion %></p>
        
        <form action="/canciones/confirmar" method="post">
            <input type="hidden" name="nombre" value="<%= nombre %>">
            <input type="hidden" name="autor" value="<%= autor %>">
            <input type="hidden" name="duracion" value="<%= duracion %>">
            <input type="hidden" name="fecha_creacion" value="<%= fecha_creacion %>">
            <input type="hidden" name="calificacion" value="<%= calificacion %>">
            <input type="submit" value="Continuar">
        </form>
    </body>
</html>
