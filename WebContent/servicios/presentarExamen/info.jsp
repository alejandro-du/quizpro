<%-- 
    Author     : Alejandro
    Description: Presenta la información de bienvenida al examen.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<head>
    <title>Información de examen</title>
</head>

<body>
    
    <h2>
        <c:out value="${examen.nombre}" />
    </h2>
    
    <p>
        <div class="nota">
            Recuerde que dispone del tiempo indicado para la presentación del examen.
        </div>
    </p>
    
    <%--<p>
        Duración del examen: <c:out value="${examen.duracion}" escapeXml="false" /> minutos.
    </p>
    --%>
    
    <p>
        <c:out value="${examen.descripcion}" escapeXml="false" />
    </p>
    
    <s:actionerror />
    
    <s:form action="mostrarPregunta!cargarPostulante">
        <s:textfield label="Documento" name="postulante.documento" required="true" />
        <s:password label="Contraseña" name="postulante.password" required="true" />
        <s:submit value="Iniciar el examen" />
    </s:form>

</body>
