<%-- 
    Author     : Alejandro
    Description: Presenta resultados.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<head>
    <title>Resultados</title>
</head>

<body>
    
    Examen: <s:property value="examen.nombre" />
        
    <p>
        <div class="nota">
            NOTA: Todos los puntajes están en una escala de 0 a 100.
        </div>
    </p>

    <table class="crud">
        
        <thead>
            <tr><th colspan="2">Datos del postulante</th></tr>
        </thead>
        
        <tbody>
            <tr>
                <td style="padding: 10px;">Documento</td>
                <td><s:property value="postulante.documento" /></td>
            </tr>
            <tr>
                <td style="padding: 10px;">Apellido(s)</td>
                <td><s:property value="postulante.apellido" /></td>
            </tr>
            <tr>
                <td style="padding: 10px;">Nombre(s)</td>
                <td><s:property value="postulante.nombre" /></td>
            </tr>
        </tbody>
        
        <thead>
            <tr><th colspan="2">Resultados parciales</th></tr>
        </thead>
        
        <tbody>
            <s:iterator value="resultados" >

                <tr>
                    <td style="padding: 10px;">
                        <s:property value="tema.nombre" />
                    </td>
                    <td>
                        <s:property value="puntaje" />
                    </td>
                </tr>

            </s:iterator>

            <thead>
                <tr><th colspan="2">Puntaje final</th></tr>
            </thead>
        
            <tr>
                <td colspan="2" style="padding: 10px;">
                    <s:property value="resultadoFinal.puntaje" />
                </td>
            </tr>
            <tr>
                <td colspan="2" style="padding: 10px;">
                    <strong>
                        <s:if test="%{postulante.clasificado}">
                            El postulante ha clasificado.
                        </s:if>

                        <s:else>
                            El postulante NO ha clasificado
                        </s:else>
                    </strong>
                </td>
            </tr>
        </tbody>
    </table>
    
    <p>
        <div class="nota">
            NOTA: Este informe no es válido como certificado y no se aceptarán reclamos fundamentados en el mismo.
        </div>
    </p>

</body>
