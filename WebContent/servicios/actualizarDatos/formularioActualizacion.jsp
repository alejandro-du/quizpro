<%-- 
    Author     : Jorge
    Descripcion: formulario para actualizar datos del usuario.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Datos</title>
    </head>
    <body>
        
            Actualizar datos de usuario:
            <s:actionerror />
        <p>
            <s:form action="/actualizarDatos/actualizarDatos.action" cssClass="ram">
                    <td colspan="2">
                        <div class="nota">
                            Deje el siguiente campo vacio si no desea modificar el nombre actual.
                        </div>
                    </td>
                <s:textfield label="Nombre" name="nombre"/>
                    <td colspan="2">
                        <div class="nota">
                            Deje los siguientes campos vacios si no desea modificar la contraseña actual.
                        </div>
                    </td>
                <s:password label="Contraseña actual" name="password" />
                <s:password label="Contraseña nueva" name="nuevoPassword"/>
                <s:password label="Confirmar contraseña nueva" name="nuevoPassword2"/>
            <s:submit value="Cambiar Datos" />

            </s:form>
        </p>
    </body>
</html>