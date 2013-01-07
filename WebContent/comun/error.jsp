<%-- 
    Author     : Alejandro
    Descripcion: JSP para mostrar errores.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        
        Ha ocurrido un error en la petición. Por favor, intente de nuevo. Si el problema persiste, comuníquese con el Royal Art Museum.
        
        <p>
            <s:actionmessage />
            <s:actionerror />
        </p>
        
    </body>
</html>
