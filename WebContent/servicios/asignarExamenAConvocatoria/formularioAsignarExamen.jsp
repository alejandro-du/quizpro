<%-- 
    Author     : Alejandro
    Descripcion: Formulario para seleccionar una convocatoria y un examen.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar examen a convocatoria</title>
    </head>
    <body>
        
        <s:actionerror />
        
        <p>
            <s:form action="/asignarExamenAConvocatoria/asignar.action" cssClass="ram">
                
                <s:select label="Convocatoria"
                          name="convocatoria.id"
                          value="%{convocatoria.id}"
                          list="listaConvocatorias"
                          listValue="nombre"
                          listKey="id"
                          headerValue="[Seleccione]"
                          headerKey="null"
                          required="true"
                />
 
                <s:select label="Examen"
                          value="%{examen.id}"
                          name="examen.id"
                          list="listaExamenes"
                          listKey="id"
                          listValue="nombre"
                          headerValue="[Seleccione]"
                          headerKey="null"
                          required="true" />

                 <s:submit value="Asignar" />

            </s:form>
        </p>
    </body>
</html>