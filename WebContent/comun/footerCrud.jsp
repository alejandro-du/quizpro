<%-- 
    Author     : Alejandro
    Descripcion: Footer para los CRUDs.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<p>
    <!-- configurar link de la parte inferior -->
    <s:if test="%{accion=='buscar'}">
        <s:url id="crear" value="%{'/' + paquete + '/formulario_' + paquete + '.action?accion=crear'}" />
        <a href="${crear}">Crear nuevo</a>
    </s:if>
    <s:else>
        <s:url id="volver" value="%{'/' + paquete + '/formulario_' + paquete + '.action?accion=buscar'}" />
        <a href="${volver}">Volver al inicio</a>
    </s:else>

<p/>
