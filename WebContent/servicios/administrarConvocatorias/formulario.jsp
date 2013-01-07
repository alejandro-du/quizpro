<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar convocatorias.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar convocatorias</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Nombre" name="convocatoria.nombre" required="%{requerido}" />
                
                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                    <s:textfield label="Vacantes" name="convocatoria.vacantes" required="%{requerido}" />
                </s:if>
                
                <s:textfield label="Fecha de inicio de presentación del examen" name="convocatoria.fechaInicio" id="fechaInicio" readonly="true" required="false" />
                <s:textfield label="Fecha de finalización de presentación del examen" name="convocatoria.fechaFin" id="fechaFin" readonly="true" required="false" />
                
                <%@ include file="/comun/calendar.jsp" %>
                <script>calendar("fechaInicio", "fechaInicio", true)</script>
                <script>calendar("fechaFin", "fechafin", true)</script>

                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                    
                    <tr>
                        <td></td>
                        <td width="600px">
                            <s:textarea label="Descripción" id="editor" name="convocatoria.descripcion"/>
                            <%@ include file="/comun/editor.jsp" %>
                            <script>editor("editor", 500)</script>
                        </td>
                    </tr>
                    
                </s:if>
                
                <s:hidden name="convocatoria.id" value="%{convocatoria.id}" />
                <s:hidden name="convocatoria.examen.id" value="%{convocatoria.examen.id}" />
                
                <s:submit value="%{submit}" />

            </s:form>
            
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
