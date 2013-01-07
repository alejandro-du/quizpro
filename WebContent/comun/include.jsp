<%-- 
    Author     : Alejandro
    Description: Define elementos necesarios en varias jsps.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:url id="urlStyles" value="/css/styles.css" />
<s:url id="urlDisplaytag" value="/css/displaytag.css" />
<s:url id="urlCalendarStyle" value="/css/calendar.css" />

<link rel="stylesheet" type="text/css" href="${urlStyles}" />
<link rel="stylesheet" type="text/css" href="${urlDisplaytag}" />

<link rel="stylesheet" type="text/css" href="${urlCalendarStyle}" />
