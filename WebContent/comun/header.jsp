<%-- 
    Author     : Alejandro
    Descripcion: Encabezado para decorar las páginas.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

            
<div id="header">
    
    <%@ page import="ingswii.quizpro.presentacion.comun.ISesion" %>
    <%@ page import="ingswii.quizpro.biz.vo.ram.Convocatoria" %>
    
    <%
        Convocatoria convocatoria = (Convocatoria) session.getAttribute(ISesion.CONVOCATORIA);
        String nombreConvocatoria = "";
        
        if(convocatoria != null) { // ya se ha seleccionado una convocatoria?
            // cargar el nombre de la convocatoria
            nombreConvocatoria = "Convocatoria: " + convocatoria.getNombre();
        }
        
        // poner a disposición del javascript
        pageContext.setAttribute("nombreConvocatoria", nombreConvocatoria);
    %>

    <table width="100%" cellspacing="0" cellpadding="0">
        <tr>

            <td>
                <s:url id="urlRAM" value="/" includeParams="false"/>
                <a href="${urlRAM}">Royal Art Museum</a>
            </td>

            <td class="convocatoria">
                <c:out value="${nombreConvocatoria}" />
            </td>

        </tr>
    </table>

    <div id="domMenu_BJ"></div>

    <%@ include file="/comun/menu.jsp" %>

</div>
