<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar temas.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar temas</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Nombre" name="tema.nombre" required="%{requerido}" />

                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                
                    <s:select label="Ponderación"
                              name="tema.ponderacion"
                              list="#{1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9, 10:10}"
                              required="%{requerido}"
                              value="%{tema.ponderacion}" />
                </s:if>
                
                <s:textarea label="Descripción" name="tema.descripcion" rows="7" cols="50" required="false" />
                
                <s:submit value="%{submit}" />
                
                <s:hidden name="tema.id" value="%{tema.id}" />

            </s:form>
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
