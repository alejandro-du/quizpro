<%-- 
    Author     : Jorge
    Description: Muestra el formulario para inscripcion a postulante.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Inscripción</title>
    </head>
    <body>
        
            Para inscribirse a la convocatoria debe ingresar los siguientes datos.
            <s:actionerror />
        
        <p>
            <s:form action="/inscripcion/realizarInscripcion.action" cssClass="ram">
            
                <s:textfield label="Nombre" name="postulante.nombre" required="true" />
                <s:textfield label="Apellido" name="postulante.apellido" required="true" />
                <s:textfield label="Documento" name="postulante.documento" required="true" />
                <s:textfield label="E-mail" name="postulante.email" required="true" />
                <s:textfield label="Teléfono" name="postulante.telefono" required="true" />
                <s:password label="Contraseña" name="postulante.password" required="true" />
                <s:password label="Confirmar contraseña" name="password2" required="true" />
                <s:submit value="Inscribirse" />
            
            </s:form>
        </p>
    </body>
</html>
