<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para autenticarse ante el sistema.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
    </head>
    <body>
        
        Para acceder a los servicios privados del sistema debe autenticarse.
        
        <p>
            <s:form action="/autenticacion/autenticar.action" cssClass="ram">

                <s:textfield label="Login" name="usuario.login" required="true" />
                <s:password label="Contraseña" name="usuario.password" required="true" />
                <s:submit value="Autenticarse" />

            </s:form>
        </p>
    </body>
</html>
