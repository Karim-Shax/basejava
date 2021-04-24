<%--
  Created by IntelliJ IDEA.
  User: KARIM-Shakh
  Date: 11.04.2021
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <table cellpadding="2">

        <c:forEach var="Entry" items="${resume.info}">

            <jsp:useBean id="Entry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType,com.urise.webapp.model.Section>"/>
            <tr>
                <td colspan="2"><h3><a name="type.name"><c:out value="${Entry.key.title}"/></a></h3></td>
            </tr>
            <c:if test="${(Entry.key.name()=='PERSONAL')||(Entry.key.name()=='OBJECTIVE')}">
                <tr>
                    <td colspan="2">
                        <em> <c:out value="${Entry.value.getText()}"/></em>
                    </td>
                </tr>
            </c:if>
            <c:if test="${(Entry.key.name()=='ACHIEVEMENT')||(Entry.key.name()=='QUALIFICATIONS')}">
                <tr>
                    <td colspan="2">
                        <ul>
                            <c:forEach var="item" items="${Entry.value.getItems()}">
                                <li><em><c:out value="${item}"/></em></li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:if>
            <c:if test="${(Entry.key.name()=='EXPERIENCE')||(Entry.key.name()=='EDUCATION')}">
                <c:forEach var="organization" items="${Entry.value.getDetail()}">
                    <tr>
                        <td>
                            <h4>
                                <a href="${organization.getHomePage().getUrl()}"><c:out value="${organization.getHomePage().getTitle()}"/></a>
                            </h4>
                        </td>
                    </tr>
                    <c:forEach var="period" items="${organization.getList()}">
                        <jsp:useBean id="period" type="com.urise.webapp.model.Organization.Period"/>
                        <tr>
                            <td width="15%" style="vertical-align: top">
                                <%=period.viewDate()%>
                            </td>
                            <td>
                                <em>
                                    <c:out value="${period.title}"/>
                                    <c:out value="${period.getTechnoLogyNameVersion()}" />
                                </em>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
