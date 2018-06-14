<%--
  Created by IntelliJ IDEA.
  User: lantoev
  Date: 2018/4/27
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>home</title>
    <style type="text/css">
        video#bgmov {
            position: fixed; right: 0; bottom: 0;
            min-width: 100%; min-height: 100%;
            width: auto; height: auto; z-index: -100;
            background-size: cover;
        }
    </style>
</head>
<body>

<video  autoplay loop poster="/static/bg.png"  id="bgmov">
    <source src="http://localhost:8888/static/fire.mp4" type="video/mp4">
</video>
    ${time}
    <h1>${classpath}</h1>
<h1>${classpath1}</h1>
<h1>${classpath2}</h1>
    <h1>${path3}</h1>
    ${welcome}
    <s:message code="welcome"/>

    <video  autoplay="autoplay" poster="/static/bg.png" loop id="bgmov">
        <source src="/static/bg.mov" type="video/mov">
    </video>


<table>

    <c:forEach items="${files}" var="file" >
        <tr>
            <td>
               Item :  <c:out value="${file}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
