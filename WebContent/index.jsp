<%-- 
    Author     : Alejandro
    Description: JSP de inicio para usuarios no autenticados. Redirecciona al servicio seleccionar convocatoria.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<c:redirect url="/servicios/seleccionarConvocatoria/" />
