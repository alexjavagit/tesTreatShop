<jsp:include page="header.jsp"></jsp:include>

<h1>Access denied for ${login}!</h1>

<c:url value="/logout" var="logoutUrl"/>
<p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
<jsp:include page="footer.jsp"></jsp:include>