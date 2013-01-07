<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para introducir un documento y contraseña de postulante.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar resultados</title>
    </head>
    <body>
        
        Por favor, indique sus datos.
        
        <s:actionerror />
        
        <p>
            <s:form action="/consultarResultados/consultar.action" cssClass="ram">

                <s:textfield label="Documento" name="postulante.documento" required="true" />
                <s:password label="Contraseña" name="postulante.password" required="true" />
                <s:submit value="Ver resultados" />

            </s:form>
        </p>
    </body>
</html>
