<%-- 
    Author     : Alejandro
    Descripcion: Muestra el formulario para buscar/ver/crear/modificar preguntas.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar preguntas</title>
    </head>
    
    <body>
        
        <%@ include file="/comun/etiquetasCrud.jsp" %>
        
        <p>
            <s:form action="%{action}" cssClass="ram">

                <s:select label="Examen"
                          name="pregunta.examen.id"
                          list="listaExamenes"
                          listKey="id"
                          listValue="nombre"
                          required="%{requerido}"
                          value="%{pregunta.examen.{id}}" />

                <s:if test="%{accion=='modificar' || accion=='crear' || accion=='ver'}">
                    
                    <s:select label="Ponderación"
                              name="pregunta.ponderacion"
                              list="#{1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9, 10:10}"
                              required="%{requerido}"
                              value="%{pregunta.ponderacion}" />

                    <s:select label="Tema"
                              name="pregunta.tema.id"
                              list="listaTemas"
                              listKey="id"
                              listValue="nombre"
                              required="%{requerido}"
                              value="%{pregunta.tema.{id}}" />
                
                </s:if>
                
                <tr>
                    <td></td>
                    <td width="600px">
                        <s:textarea label="Texto" id="texto" name="pregunta.texto" required="%{requerido}" />
                        <%@ include file="/comun/editor.jsp" %>
                        <script>editor("texto", 350)</script>
                    </td>
                </tr>
                
                <s:if test="%{accion=='modificar' || accion=='crear'}">

                    <tr>
                        <td></td>
                        <td width="600px">
                            <s:textarea label="Respuesta 1 (correcta)" id="editor1" name="respuesta1" required="%{requerido}" />
                            <%@ include file="/comun/editor.jsp" %>
                            <script>editor("editor1", 220)</script>
                        </td>
                    </tr>
                
                    <tr>
                        <td></td>
                        <td width="600px">
                            <s:textarea label="Respuesta 2" id="editor2" name="respuesta2" required="%{requerido}" />
                            <%@ include file="/comun/editor.jsp" %>
                            <script>editor("editor2", 220)</script>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td width="600px">
                            <s:textarea label="Respuesta 3" id="editor3" name="respuesta3" required="%{requerido}" />
                            <%@ include file="/comun/editor.jsp" %>
                            <script>editor("editor3", 220)</script>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td width="600px">
                            <s:textarea label="Respuesta 4" id="editor4" name="respuesta4" required="%{requerido}" />
                            <%@ include file="/comun/editor.jsp" %>
                            <script>editor("editor4", 220)</script>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td width="600px">
                            <s:textarea label="Respuesta 5" id="editor5" name="respuesta5" required="%{requerido}" />
                            <%@ include file="/comun/editor.jsp" %>
                            <script>editor("editor5", 220)</script>
                        </td>
                    </tr>
                    
                </s:if>
                
                <s:submit value="%{submit}" />
                
                <s:hidden name="pregunta.id" value="%{pregunta.id}" />
                
            </s:form>

        </p>
        
         <%@ include file="/comun/footerCrud.jsp" %>

    </body>
</html>
