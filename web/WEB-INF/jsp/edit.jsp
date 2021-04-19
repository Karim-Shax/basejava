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
                <button type="submit" name="save" value="edit" onclick="document.form.submit();">Сохранить</button>
                <button type="button" onclick="window.history.back()">Отмена</button>
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
                <c:if test="${type.name()=='PERSONAL'}">
                    <dd>
                                <input size="120" name="${type.name()}"
                                          value="${resume.getSection(type).getText()}">
                        <br/>
                    </dd>
                </c:if>
                <c:if test="${type.name()=='OBJECTIVE'}">
                    <dd>
                                <input size="120" name="${type.name()}"
                                          value="${resume.getSection(type).getText()}">
                        <br/>
                    </dd>
                </c:if>
                <c:if test="${type.name()=='ACHIEVEMENT'}">
                    <button type="button" onclick="makeSectionFormAch()">Добавить</button>

                    <dd id="ACHIEVEMENT">
                        <c:forEach var="item" items="${resume.getSection(type).getItems()}">
                            <input size="120" name="${type.name()}" value="${item}">
                            <br/>
                        </c:forEach>
                    </dd>
                    <script>
                        function makeSectionFormAch() {
                            input = document.createElement('input');
                            input.size = 100;
                            input.type = 'text'
                            input.name = 'ACHIEVEMENT';
                            document.getElementById('ACHIEVEMENT').appendChild(input);
                        }
                    </script>
                </c:if>
                <c:if test="${type.name()=='QUALIFICATIONS'}">
                    <button type="button" onclick="makeSectionFormQual()">Добавить</button>
                    <dd id="QUALIFICATIONS">
                        <c:forEach var="item" items="${resume.getSection(type).getItems()}">
                            <input size="120" name="${type.name()}" value="${item}">
                            <br/>
                        </c:forEach>
                    </dd>
                    <script>
                        function makeSectionFormQual() {
                            input = document.createElement('input');
                            input.size = 100;
                            input.type = 'text'
                            input.name = 'QUALIFICATIONS';
                            document.getElementById('QUALIFICATIONS').appendChild(input);
                        }
                    </script>
                </c:if>
                <c:if test="${type.name()=='EXPERIENCE'}">
                    <button type="button" id="sect" name="${type.name()}">
                        Добавить
                    </button>
                    <script>
                        $(function () {
                            $('#sect').click(function () {
                                var content = $('#EXPERIENCE').html();
                                if (content == null) {
                                    content = $('#sectionIn').html();
                                }
                                var newdiv = $("<div>");
                                newdiv.html(content);
                                $('#sect').after(newdiv);
                            });
                        });
                    </script>
                    <c:if test="${(resume.getSection(type).getDetail()==null)||(resume.getSection(type).getDetail().isEmpty())}">
                        <div id="sectionIn">
                            <dd>
                                Кампания <input type="text" name="${type.name()}"
                                               size=60"><br/> <br/>
                                Url Адрес <input type="text" name="${type.name()}" size=60"><br/> <br/>
                            </dd>
                            <dd>
                                Начало
                                <input type="date" name="${type.name()}"
                                       size=60><br/> <br/>
                                Конец
                                <input type="date"
                                       name="${type.name()}" size=60><br/> <br/>
                                Позиция
                                <input type="text"
                                       name="${type.name()}"
                                       size=60><br/> <br/>
                                Описание
                                <input type="text"
                                       name="${type.name()}"
                                       size=60><br/> <br/><br/> <br/>
                            </dd>
                            <br/>
                        </div>
                    </c:if>
                    <c:forEach var="organization" items="${resume.getSection(type).getDetail()}">
                        <div id="EXPERIENCE">
                            <dd>
                                Кампания <input type="text" name="${type.name()}"
                                               value="${organization.homePage.getTitle()}"
                                               size=60"><br/> <br/>
                                Url Адрес <input type="text" name="${type.name()}" value="${organization.homePage.getUrl()}"
                                           size=60"><br/>
                                <br/>
                            </dd>
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
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${type.name()=='EDUCATION'}">
                    <button type="button" id="sect2" name="${type.name()}"> Добавить
                    </button>
                    <script>
                        $(function () {
                            $('#sect2').click(function () {
                                var content = $('#EDUCATION').html();
                                if (content == null) {
                                    content = $('#sectionInE').html();
                                }
                                var newdiv = $("<div>");
                                newdiv.html(content);
                                $('#sect2').after(newdiv);
                            });
                        });
                    </script>
                    <c:if test="${(resume.getSection(type).getDetail()==null)||(resume.getSection(type).getDetail().isEmpty())}">
                        <div id="sectionInE">
                            <dd>
                                Кампания <input type="text" name="${type.name()}"
                                               size=60"><br/> <br/>
                                Url Адрес <input type="text" name="${type.name()}" size=60"><br/>
                                <br/>
                            </dd>
                            <dd>
                                Начало
                                <input type="date" placeholder="dd-MM-yyyy" name="${type.name()}"
                                       size=60><br/> <br/>
                                Конец
                                <input type="date" placeholder="dd-MM-yyyy"
                                       name="${type.name()}" size=60><br/> <br/>
                                Позиция
                                <input type="text"
                                       name="${type.name()}"
                                       size=60><br/> <br/>
                                Описание
                                <input type="text"
                                       name="${type.name()}"
                                       size=60><br/> <br/><br/> <br/>
                            </dd>
                        </div>
                    </c:if>
                    <c:forEach var="organization" items="${resume.getSection(type).getDetail()}">
                        <div id="EDUCATION">
                            <dd>
                                Кампания <input type="text" name="${type.name()}"
                                               value="${organization.homePage.getTitle()}"
                                               size=60"><br/> <br/>
                                Url Адрес <input type="text" name="${type.name()}" value="${organization.homePage.getUrl()}"
                                           size=60"><br/>
                                <br/>
                            </dd>
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