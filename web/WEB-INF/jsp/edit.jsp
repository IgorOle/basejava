<%@ page import="com.igorole.basejava.webapp.model.ContactType" %>
<%@ page import="com.igorole.basejava.webapp.model.SectionType" %>
<%@ page import="com.igorole.basejava.webapp.view.ToHTML" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="resume" type="com.igorole.basejava.webapp.model.Resume" scope="request"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            <c:forEach var="type1" items="<%=SectionType.values()%>">
                <jsp:useBean id="type1" type="com.igorole.basejava.webapp.model.SectionType"/>
                $('#add${type1.name()}').on('click', function () {
                    $('#${type1.name()}').append("<%=ToHTML.getSectionInputTag(type1, null)%>");
                });
            </c:forEach>
        });
    </script>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container center-block">
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded" >
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <div class="form-group">
            <dl>
                <h3>Имя:</h3>
                <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
            </dl>
            <h3>Контакты:</h3>
            <div class="container center-block">
                <c:forEach var="type" items="<%=ContactType.values()%>">
                    <div class="row">
                        <div class="input-group">
                            <span class="input-group-addon"  style="width: 200px">${type.title}</span>
                            <input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"
                                   class="form-control" placeholder="${type.title}" aria-describedby="basic-addon1">
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="container center-block">
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <jsp:useBean id="type" type="com.igorole.basejava.webapp.model.SectionType"/>
                    <div class="row">
                            <div class="input-group-lg " >
                                <label class="col-xs-2 control-label">
                                    ${type.title}
                                    <c:if test="${type==SectionType.ACHIEVEMENT || type==SectionType.QUALIFICATIONS}">
                                        <a href="" onclick="return false;" id="add${type.name()}"><i
                                            class="glyphicon glyphicon-plus-sign"></i></a>
                                    </c:if>
                                </label>
                                <div id="${type.name()}">
                                    <%=ToHTML.getSectionInputTag(type, resume.getSection(type))%>
                                </div>
                            </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <hr>
        <button name="save" type="submit" class="btn btn-default">Сохранить</button>
        <button onclick="window.history.back()" class="btn">Отменить</button>
    </form>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>