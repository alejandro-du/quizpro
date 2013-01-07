<%-- 
    Author     : Alejandro
    Description: Presenta una pregunta con sus opciones.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>
 
<head>
    <title>${examen.nombre}</title>
</head>

 <body>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.util.Calendar" %>

<span id="liveclock" style="position:absolute;right:180;top:145;"></span>

    <script>
        var Digital=new Date(0,0,0,0,0)
        function ini(){
            <%
            double tiempoRestante = ((Double)(session.getAttribute("tiempoRestante")));
            %>
            var tiempoRestante = "<%=tiempoRestante%>";
            Digital.setMilliseconds(tiempoRestante)
            show5()
        }
        function show5(){
            if (!document.layers&&!document.all&&!document.getElementById)
                return
            var hours=Digital.getHours()
            var minutes=Digital.getMinutes()
            var seconds=Digital.getSeconds()

            if (minutes<=9)
                minutes="0"+minutes
            if (seconds<=9)
                seconds="0"+seconds
            myclock="<font size='5' face='Arial' ><b><font size='1'>Tiempo Restante:</font></br>"+hours+":"+minutes+":"
             +seconds+"</b></font>"
            if (document.layers){
                document.layers.liveclock.document.write(myclock)
                document.layers.liveclock.document.close()
            }
            else if (document.all)
                liveclock.innerHTML=myclock
            else if (document.getElementById)
                document.getElementById("liveclock").innerHTML=myclock
            
            
            if (hours!=0 || minutes!=0 || seconds!=0 ){
                Digital.setSeconds(Digital.getSeconds()-1)
                setTimeout("show5()",1000)
            }
            else {
                //alert('Ha concluido el tiempo del examen')
                document.respuesta.action="mostrarPregunta!responder.action"
                document.respuesta.submit()
            }
         }


        window.onload=ini

     </script>
 
 
 <h2>
    <c:out value="Preguntas por contestar: ${preguntasFaltantes}"/>
 </h2>
 <p>   
    <div class="nota">
        <c:out value="Tema: ${tema.nombre}"/>
    </div>
 </p>
    <c:out value="${pregunta.texto}" escapeXml="false" />
    
     <s:actionerror />
     
     <s:form name="respuesta" cssClass="ram" action="mostrarPregunta!responder">
         <s:radio list="pregunta.respuestas" listValue="texto" listKey="id" name="seleccion"  labelposition="top" required="true"  />
        <s:submit value="Enviar" />      
    </s:form>
    
</body>
