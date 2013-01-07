<%-- 
    Author     : Alejandro
    Descripcion: Presenta los resultados de búsqueda de ips autorizadas.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar IPs</title>
    </head>
    
    <body>
        
        Resultados de la búsqueda.
        
        <p>
            <display:table name="lista" export="true" decorator="ingswii.quizpro.presentacion.comun.impl.DecoradorCrud" requestURI="buscar_administrarIps.action" pagesize="20">

                <display:column property="id" sortable="true" />
                <display:column property="ip" title="IP" sortable="true" />
                <display:column property="alias" sortable="true" />
                <display:column property="opciones" media="html" />

            </display:table>
            
        </p>
        
        <%@ include file="/comun/footerCrud.jsp" %>
        
        
    </body>
</html>
