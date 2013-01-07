<%-- 
    Author     : Alejandro
    Description: Formulario de selección de convocatoria.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<head>
    <title>Bienvenido</title>
</head>

<body>
    
    <s:actionmessage />

    Por favor, seleccione la convocatoria a la que desea acceder.

    <p>
        <s:form label="Seleccione una convocatoria" action="/seleccionarConvocatoria/establecerConvocatoriaSesion.action" cssClass="ram">

            <s:select label="Seleccione una convocatoria"
                      name="convocatoria.id"
                      list="listaConvocatorias"
                      listValue="nombre"
                      listKey="id"
                      headerValue="[Seleccione]"
                      headerKey="null"
                      required="true"
            />

            <s:submit value="Continuar" />

        </s:form>
    </p>

</body>
