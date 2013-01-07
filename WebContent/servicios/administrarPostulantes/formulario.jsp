<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar postulantes.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar postulantes</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Documento" name="postulante.documento" required="%{requerido}" />
                <s:textfield label="Apellidos" name="postulante.apellido" required="%{requerido}" />
                <s:textfield label="Nombres" name="postulante.nombre" required="%{requerido}" />
                <s:textfield label="Teléfono" name="postulante.telefono" required="%{requerido}" />
                <s:textfield label="E-mail" name="postulante.email" required="%{requerido}" />
                
                <s:if test="%{accion=='modificar'}">
                    <td colspan="2">
                        <div class="nota">
                            Deje los siguientes campos vacios si no desea modificar la <br/>
                            contraseña actual.
                        </div>
                    </td>
                </s:if>
                
                <s:if test="%{accion=='crear' || accion=='modificar'}">
                    <s:password label="Contraseña" name="postulante.password" required="false" />
                    <s:password label="Confirmar contraseña" name="password2" required="false" />
                </s:if>
                
                <s:submit value="%{submit}" />
                
                <s:hidden name="postulante.id" value="%{postulante.id}" />

            </s:form>
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
