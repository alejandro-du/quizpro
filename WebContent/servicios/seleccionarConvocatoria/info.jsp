<%-- 
    Author     : Alejandro
    Description: Presenta la información de bienvenida al seleccionar una convocatoria.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<head>
    <title>Bienvenido</title>
</head>

<body>
    
    La convocatoria ha sido seleccionada. Use el menú para acceder a los servicios del sistema.
    
    <p>
        <div class="nota">
            Para una correcta navegación por el sitio, use las opciones que ofrece la aplicación.
            No use los botones "Anterior" ni "Siguiente" de su navegador.
        </div>
    </p>

    <p>
        <c:out value="${convocatoria.descripcion}" escapeXml="false"/>
    </p>
    
    <p>
        <s:url id="inscripcion" value="/servicios/inscripcion/" />
        Para inscribirse haga <a href="${inscripcion}">clic aquí</a>.
    </p>

</body>
