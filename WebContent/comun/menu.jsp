<%-- 
    Author     : Alejandro
    Descripción: Muestra el menú de la sesión.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/comun/include.jsp" %>

<s:url id="urlMenuLib" value="/scripts/domMenu/domLib.js" />
<s:url id="urlMenu" value="/scripts/domMenu/domMenu.js" />
<s:url id="urlDomMenuStyles" value="/css/domMenu.css" />

<link rel="stylesheet" type="text/css" href="${urlDomMenuStyles}" />

<div>

    <%@ page import="ingswii.quizpro.presentacion.comun.ISesion" %>
    
    <script src="${urlMenuLib}"></script>
    <script src="${urlMenu}"></script>
    
    <%
        // cargar la convocatoria de la sesión
        String scriptMenu = null;
        
        // cargar el script del menu de la sesión.
        scriptMenu = (String) session.getAttribute(ISesion.JAVASCRIPT_MENU);
        // poner a disposición del javascript
        pageContext.setAttribute("scriptMenu", scriptMenu);
    %>

    <script>

        document.onmouseup = function()
        {
            // desactivar menú al hacer click
            domMenu_deactivate('domMenu_BJ');
        }

        // configuración del menú
        domMenu_settings.set('domMenu_BJ', new Hash(
            'menuBarWidth',              '0%',
            'menuBarClass',              'BJ_menuBar',
            'menuElementClass',          'BJ_menuElement',
            'menuElementHoverClass',     'BJ_menuElementHover',
            'menuElementActiveClass',    'BJ_menuElementActive',
            'subMenuBarClass',           'BJ_subMenuBar',
            'subMenuElementClass',       'BJ_subMenuElement',
            'subMenuElementHoverClass',  'BJ_subMenuElementHover',
            'subMenuElementActiveClass', 'BJ_subMenuElementHover',
            'subMenuMinWidth',           'auto',
            'distributeSpace',           false,
            'openMouseoverMenuDelay',    0,
            'openMousedownMenuDelay',    0,
            'penMouseoverSubMenuDelay',  0,
            'closeClickMenuDelay',       0,
            'closeMouseoutMenuDelay',    650,
            'expandMenuArrowUrl',        'arrow.gif'
        ));

        // construir el menú
        
        if('<c:out value="${scriptMenu}" />' != "") {

            domMenu_data.set('domMenu_BJ', new Hash(
                <c:out escapeXml="false" value="${scriptMenu}" />
            ));

            // activar el menú
            domMenu_activate('domMenu_BJ');
            //buildmenu('mainmenu');
        }

    </script>
    
</div>
