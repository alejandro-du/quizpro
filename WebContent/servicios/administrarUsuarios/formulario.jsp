<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar usuarios.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar usuarios</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:textfield label="Documento" name="usuario.documento" required="%{requerido}" />
                <s:textfield label="Nombre" name="usuario.nombre" required="false" />
                <s:textfield label="Login" name="usuario.login" required="%{requerido}" />
                
                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                
                    <s:select label="Grupos"
                              name="gruposSeleccionados.id"
                              list="listaGrupos"
                              listKey="id"
                              listValue="nombre"
                              multiple="true"
                              size="6"
                              required="false"
                              value="%{usuario.grupos.{id}}" />
                
                </s:if>

                <s:if test="%{accion=='modificar'}">
                    <td colspan="2">
                        <div class="nota">
                            Deje los siguientes campos vacios si no desea modificar la <br/>
                            contraseña actual.
                        </div>
                    </td>
                </s:if>
                
                <s:if test="%{accion=='crear' || accion=='modificar'}">
                    <s:password label="Contraseña" name="usuario.password" required="false" />
                    <s:password label="Confirmar contraseña" name="password2" required="false" />
                </s:if>

                <s:submit value="%{submit}" />
                
                <s:hidden name="usuario.id" value="%{usuario.id}" />

            </s:form>
        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
