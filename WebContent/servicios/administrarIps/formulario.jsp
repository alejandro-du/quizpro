<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar ips autorizadas.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar IPs</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="IP" name="ip.ip" required="%{requerido}" />
                <s:textfield label="Alias" name="ip.alias" required="%{requerido}" />
                
                <s:submit value="%{submit}" />
                
                <s:hidden name="ip.id" value="%{ip.id}" />

            </s:form>
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
