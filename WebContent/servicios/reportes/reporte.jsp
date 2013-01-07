<%-- 
    Author     : Alejandro
    Descripcion: Muestra reportes.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte</title>
    </head>
    
    <body>
        
        <h2>
            <c:out value="${reporte.nombre}" />
        </h2>
        
        <p>
            <sql:setDataSource
                driver="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/quizpro"
                user="root"
                password="admin" />
                
                <sql:query var="resultados">
                    <c:out value="${reporte.consulta}" />
                </sql:query>
            
            <display:table name="${resultados.rows}" export="true" requestURI="/reportes/reporte.action" pagesize="50"/>
            
        </p>
        
    </body>
</html>
