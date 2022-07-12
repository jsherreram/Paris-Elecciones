<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="menuHelper" class="co.com.grupoasd.nomina.portal.helper.MenuHelper" scope="request">
    <jsp:setProperty name="menuHelper" property="request" value="${pageContext.request}" />
</jsp:useBean>

<div id="navigation">
    <ul class="nav" id="side-menu">
        <c:forEach items="${menuHelper.menu}" var="item">
            <li>
                <a href="${item.href}"> <span class="nav-label">${item.label}</span></a>
            </li>
       </c:forEach>
    <ul>
</div>
