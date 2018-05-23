<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lantoev
  Date: 2018/5/17
  Time: 下午2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误界面</title>

</head>
<body>
<p>${exception.localizedMessage}</p>
        <p>${pageContext.response.status}</p>
        <p><c:forEach items="${exception.stackTrace}" var="item">
        <p>
        <span style="text-decoration:underline;text-underline-color: crimson;color:red;">
            ${item.className}.${item.methodName} ${item.lineNumber} ${item.toString()}
        </span>
</p>
        </c:forEach> </p>

        <p>
            <% out.write(pageContext.getException() == null?"yes":"no");%>
        </p>
</body>
</html>
