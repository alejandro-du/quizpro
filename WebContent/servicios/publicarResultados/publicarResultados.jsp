<%-- 
    Author     : Alejandro
    Descripcion: Formulario para confirmar la publicación de los resultados.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicar resultados</title>
    </head>
    <body>
        
        Por favor confirme que desea publicar los resultados para esta convocatoria.
        
        <p>
            <div class="nota">
                Recuerde que este proceso incluye la calificación de los exámenes y la clasificacion de los postulantes.
            </div>
            
            <s:form action="/publicarResultados/publicar.action" >

                <s:submit value="Publicar resultados" />

            </s:form>
        </p>
    </body>
</html>
