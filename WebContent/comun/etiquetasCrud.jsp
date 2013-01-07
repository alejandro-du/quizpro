<%-- 
    Author     : Alejandro
    Descripcion: Configura los nombres de las etiquetas de los CRUDs.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<!-- colocar campos para mensajes y errores -->
<s:actionmessage />
<s:actionerror />


<!-- configurar etiquetas y actions -->
<s:if test="%{accion=='buscar'}">
    Buscar.
    <s:set name="action" value="'/' + paquete + '/buscar_' + paquete + '.action'" />
    <s:set name="submit" value="%{'Buscar'}" />
</s:if>
<s:elseif test="%{accion=='crear'}">
    Crear nuevo.
    <s:set name="action" value="'/' + paquete + '/crear_' + paquete + '.action'" />
    <s:set name="submit" value="%{'Crear'}" />
</s:elseif>
<s:elseif test="%{accion=='ver'}">
    Ver.
    <s:set name="action" value="%{'javascript: history.back()'}" />
    <s:set name="submit" value="%{'Volver'}" />
</s:elseif>
<s:elseif test="%{accion=='modificar'}">
    Modificar.
    <s:set name="action" value="'/' + paquete + '/modificar_' + paquete + '.action'" />
    <s:set name="submit" value="%{'Modificar'}" />
</s:elseif>


<!-- configurar valor para campos requeridos -->
<s:if test="%{accion=='crear' || accion=='modificar'}">
    <s:set name="requerido" value="%{true}" />
</s:if>
<s:else>
    <s:set name="requerido" value="%{false}" />
</s:else>
