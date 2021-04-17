<%--
  Created by IntelliJ IDEA.
  User: KARIM-Shakh
  Date: 13.04.2021
  Time: 16:05
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form name="menu" method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <dl>
            <dd>
                <button type="submit" name="save" value="add" onclick="document.form.submit();">Save</button>
                <button type="button" onclick="window.history.back()">Cancel</button>
            </dd>
        </dl>
        <hr>
        <input type="hidden" name="uuid" value="${resume.uuid}">

        <dl class="inline">
            <dt>Name:</dt>
            <dd><input type="text" name="fullName" size=30 value="${resume.fullName}"></dd>
        </dl>

        <h3>Contacts:</h3>
        <br>
        <c:forEach var="type" items="${ContactType.values()}">
            <dl class="inline">
                <dt>${type.title}:</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getCont(type)}">
                </dd>
            </dl>
        </c:forEach>

        <c:forEach var="type" items="${SectionType.values()}">
            <dl id="form">
                <dt>${type.name()}:</dt>
                <c:choose>

                    <c:when test="${resume.getSection(type).getClass().getSimpleName() == null}">
                        <c:if test="${type.name()=='PERSONAL'}">
                            <dd>
                                <textarea cols="60" rows="5" name="${type.name()}"></textarea>
                            </dd>
                        </c:if>
                        <c:if test="${type.name()=='OBJECTIVE'}">
                            <dd>
                                <textarea cols="60" rows="5" name="${type.name()}"></textarea>
                            </dd>
                        </c:if>
                        <c:if test="${type.name()=='ACHIEVEMENT'}">
                            <button type="button" onclick="makeSectionFormAch()"> add</button>
                            <dd id="ACHIEVEMENT">
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

                            <button type="button" onclick="makeSectionFormQual()"> add</button>
                            <dd id="QUALIFICATIONS">
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
                                add
                            </button>
                            <script>
                                $(function () {
                                    $('#sect').click(function () {
                                        var content = $('#sectionInE').html();
                                        var newdiv = $("<div>");
                                        newdiv.html(content);
                                        $('#sect').after(newdiv);
                                    });
                                });
                            </script>
                            <div id="sectionInE">
                                <dd>
                                    Company <input type="text" name="${type.name()}"
                                                   size=60"><br/> <br/>
                                    Url <input type="text" name="${type.name()}" size=60"><br/>
                                    <br/>
                                </dd>
                                <dd>
                                    From
                                    <input type="date" placeholder="dd-MM-yyyy" name="${type.name()}"
                                           size=60><br/> <br/>
                                    To Date
                                    <input type="date" placeholder="dd-MM-yyyy"
                                           name="${type.name()}" size=60><br/> <br/>
                                    Position
                                    <input type="text"
                                           name="${type.name()}"
                                           size=60><br/> <br/>
                                    Description
                                    <input type="text"
                                           name="${type.name()}"
                                           size=60><br/> <br/><br/> <br/>
                                </dd>
                            </div>
                        </c:if>
                        <c:if test="${type.name()=='EDUCATION'}">
                            <button type="button" id="sect2" name="${type.name()}"> add
                            </button>
                            <script>
                                $(function () {
                                    $('#sect2').click(function () {
                                        var content = $('#sectionIn').html();
                                        var newdiv = $("<div>");
                                        newdiv.html(content);
                                        $('#sect2').after(newdiv);
                                    });
                                });
                            </script>
                            <div id="sectionIn">
                                <dd>
                                    Company <input type="text" name="${type.name()}"
                                                   size=60"><br/> <br/>
                                    Url <input type="text" name="${type.name()}" size=60"><br/>
                                    <br/>
                                </dd>
                                <dd>
                                    From
                                    <input type="date" placeholder="dd-MM-yyyy" name="${type.name()}"
                                           size=60><br/> <br/>
                                    To Date
                                    <input type="date" placeholder="dd-MM-yyyy"
                                           name="${type.name()}" size=60><br/> <br/>
                                    Position
                                    <input type="text"
                                           name="${type.name()}"
                                           size=60><br/> <br/>
                                    Description
                                    <input type="text"
                                           name="${type.name()}"
                                           size=60><br/> <br/><br/> <br/>
                                </dd>
                            </div>
                        </c:if>
                    </c:when>
                </c:choose>

            </dl>
        </c:forEach>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
