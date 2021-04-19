<%--
  Created by IntelliJ IDEA.
  User: KARIM-Shakh
  Date: 11.04.2021
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section class="d-flex flex-column h-100">
    <table>
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th>Телефон</th>
            <th>Удалить</th>
            <th>Редактировать</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
            <tr>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a>
                </td>
                <td>
                        ${resume.getCont(ContactType.EMAIL)}
                </td>
                <td> ${resume.getCont(ContactType.PHONE)}</td>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a>
                </td>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>