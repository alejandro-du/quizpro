<%-- 
    Document   : main.jsp
    Created on : Feb 2, 2008, 11:22:38 PM
    Author     : Alejandro
    Descripcion: Decorador sitemesh que incluye una sección lateral, un header y un footer.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>


<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            QuizPro - Royal Art Museum
        </title>
    </head>

    <body>
        <div id="container">
            
            <%@ include file="/comun/header.jsp" %>
            
            <div id="sidebar">
                <h1><decorator:title /></h1>
            </div>
            
            <div id="main">
                <decorator:body />
            </div>
            
            <%@ include file="/comun/footer.jsp" %>
            
        </div>
    </body>
    
</html>
