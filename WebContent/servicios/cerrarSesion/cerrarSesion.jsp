<%-- 
    Author     : Jorge
    Descripcion: Formulario para confirmar cierre de sesion.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar sesión</title>
    </head>
    <body>
        
        Por favor confirme el cierre de la sesión.
        
        <p>
            <s:form action="/cerrarSesion/cerrarSesion.action" >

                <s:submit value="Cerrar sesión" />

            </s:form>
        </p>
    </body>
</html>
