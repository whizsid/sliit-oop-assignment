<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MusicStore Project</title>
</head>
<body>
    <c:if test="${error!=null}">
        <span>Error :- ${error} </span>
    </c:if>
    <form method="post">
        Email: <input type="email" value="${email}" name="email"/>
        &nbsp;&nbsp;
        Password: <input type="password"  name="password"/>
        &nbsp;&nbsp;
        <input type="submit" value="Login" />
    </form>
</body>
</html>