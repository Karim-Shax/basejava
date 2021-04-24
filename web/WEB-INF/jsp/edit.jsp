<%--
  Created by IntelliJ IDEA.
  User: KARIM-Shakh
  Date: 11.04.2021
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">

        <dl>
            <dd>
                <button type="submit" name="save" value="edit" onclick="document.form.submit()" >Сохранить</button>
                <button type="button" onclick="window.history.back()" >Отмена</button>
            </dd>
        </dl>

        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl class="inline">
            <dt>Имя</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}" style="font-size: large"></dd>
        </dl>

        <h4>Контакты:</h4>
        <c:forEach var="type" items="${ContactType.values()}">
            <dl class="inline">
                <dt>${type.title}:</dt>
                <dd><input type="text" name="${type.name()}" size=50 value="${resume.getCont(type)}"
                           style="font-size: medium"></dd>
            </dl>
        </c:forEach>
        <hr/>
        <dl>
            <c:forEach var="type" items="${SectionType.values()}">
            <dl id="form">
                <dt>${type.getTitle()}:</dt>
                <c:if test="${(type.name()=='PERSONAL')||(type.name()=='OBJECTIVE')}">
                    <button type="button" onclick="makeSectionFormAch('${type.name()}')" >Добавить</button>
                    <dd id="${type.name()}">
                        <c:if test="${resume.getSection(type).getText()!=null}">
                            <input size="100" name="${type.name()}"
                                   value="${resume.getSection(type).getText()}">
                            <br/>
                        </c:if>
                    </dd>
                </c:if>
                <c:if test="${(type.name()=='ACHIEVEMENT')||(type.name()=='QUALIFICATIONS')}">
                    <c:if test="${type.name()=='ACHIEVEMENT'}">
                        <button type="button" onclick="makeSectionFormAch('ACHIEVEMENT')" >Добавить</button>
                    </c:if>
                    <c:if test="${type.name()=='QUALIFICATIONS'}">
                        <button type="button" onclick="makeSectionFormAch('QUALIFICATIONS')" >Добавить</button>
                    </c:if>
                    <dd id="${type.name()}">
                        <c:forEach var="item" items="${resume.getSection(type).getItems()}">
                            <input size="120" name="${type.name()}" value="${item}">
                            <br/>
                        </c:forEach>
                    </dd>
                </c:if>
                <c:if test="${(type.name()=='EXPERIENCE')||(type.name()=='EDUCATION')}">
                    <c:if test="${type.name()=='EXPERIENCE'}">
                        <button type="button" id="sect" name="${type.name()}" >
                            Добавить Организацию
                        </button>
                    </c:if>
                    <c:if test="${type.name()=='EDUCATION'}">
                        <button type="button" id="sect1" name="${type.name()}" >
                            Добавить Организацию
                        </button>
                    </c:if>
                    <c:forEach var="organization" items="${resume.getSection(type).getDetail()}" varStatus="orgcount">
                        <div>
                            <c:if test="${organization.homePage.getTitle()!=null}">
                            <dd>
                                Кампания <input type="text" name="${type.name()}"
                                                value="${organization.homePage.getTitle()}"
                                                size=60"><br/> <br/>
                                Url Адрес <input type="text" name="${type.name()}"
                                                 value="${organization.homePage.getUrl()}"
                                                 size=60"><br/>
                                <br/>
                            </dd>
                            </c:if>
                            <c:forEach var="period" items="${organization.getList()}">
                                <dd>
                                    Начало
                                    <input type="date" placeholder="dd-MM-yyyy" name="${type.name()}"
                                           value="${period.getStartTime()}"
                                           size=60><br/> <br/>
                                    Конец
                                    <input type="date" placeholder="dd-MM-yyyy"
                                           value="${period.getEndTime()}"
                                           name="${type.name()}" size=60><br/> <br/>
                                    Позиция
                                    <input type="text"
                                           name="${type.name()}"
                                           value="${period.getTitle()}"
                                           size=60><br/> <br/>
                                    Описание
                                    <input type="text"
                                           name="${type.name()}"
                                           value="${period.getTechnoLogyNameVersion()}"
                                           size=60><br/> <br/><br/> <br/>
                                </dd>
                            </c:forEach>
                            <c:if test="${organization.homePage.getTitle()!=null}">
                            <button type="button" id="${type.name()=='EXPERIENCE'?'expos':'edpos'}+${organization.homePage.getTitle()}" onclick="addPos(this.id)">
                                Добавить Позицию
                            </button>
                            <input type="hidden" class="form-control" name="${type}" value="end">
                            </c:if>
                            <hr/>
                        </div>
                    </c:forEach>
                </c:if>
            </dl>
            </c:forEach>
            <hr>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>