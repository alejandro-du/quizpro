<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar servicios.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar servicios</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Nombre" name="servicio.nombre" required="%{requerido}" />
                <s:textfield label="Url de inicio" name="servicio.url" required="%{requerido}" />
                
                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                    
                    <s:checkbox label="Público" name="servicio.publico" required="false" />
                    <s:checkbox label="Requiere convocatoria" name="servicio.requiereConvocatoria" required="false" />

                    <s:select label="Menú"
                              name="servicio.menu.id"
                              list="listaMenus"
                              listKey="id"
                              listValue="nombre"
                              headerKey="-1"
                              headerValue="Ninguno"
                              required="false"
                              value="%{servicio.menu.{id}}" />

                </s:if>
                
                <s:submit value="%{submit}" />
                
                <s:hidden name="servicio.id" value="%{servicio.id}" />

            </s:form>
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
