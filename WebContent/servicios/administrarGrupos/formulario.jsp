<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar grupos.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar grupos</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Nombre" name="grupo.nombre" required="%{requerido}" />
                
                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                
                    <s:select label="Servicios"
                              name="serviciosSeleccionados.id"
                              list="listaServicios"
                              listKey="id"
                              listValue="nombre"
                              multiple="true"
                              size="10"
                              required="false"
                              value="%{grupo.servicios.{id}}" />
                
                </s:if>
                
                <s:submit value="%{submit}" />
                
                <s:hidden name="grupo.id" value="%{grupo.id}" />

            </s:form>
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
