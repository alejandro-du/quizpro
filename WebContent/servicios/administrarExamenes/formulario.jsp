<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar exámenes.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar exámenes</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Nombre" name="examen.nombre" required="%{requerido}" />
                
                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                    
                    <s:checkbox label="Público" name="examen.publico" required="false" />

                    <s:select label="IPs autorizadas"
                              name="ipsSeleccionadas.id"
                              list="listaIps"
                              listKey="id"
                              listValue="alias"
                              multiple="true"
                              size="10"
                              required="false"
                              value="%{examen.ips.{id}}" />

                </s:if>
                
                <s:textarea label="Descripción" name="examen.descripcion" rows="7" cols="50" required="false" />

                <s:submit value="%{submit}" />
                
                <s:hidden name="examen.id" value="%{examen.id}" />
                
            </s:form>

        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
