<%-- 
    Document   : cambiar-idioma
    Created on : 23 may 2024, 18:20:38
    Author     : agusy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
 String nuevoIdioma = request.getParameter("idioma");
 session.setAttribute("idioma", nuevoIdioma);
 response.sendRedirect("index.jsp");
%>
