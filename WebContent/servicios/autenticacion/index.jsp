<%-- 
    Author     : Alejandro
    Description: JSP de inicio para usuarios autenticados. Redirecciona al action de autenticación.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<c:redirect url="/autenticacion/inicio.action" />
