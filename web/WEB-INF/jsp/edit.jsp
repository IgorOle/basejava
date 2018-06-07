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

            function inc(name, val) {
                var obj = $('#' + name);
                var res = obj.val();
                if (val == '1')
                    obj.val(++res);
                return res;
            }

            function x() {

                $('.clsActivity').on('click', function () {
                    var type = this.id.split('_')[1];
                    var numElement = this.name;
                    alert('' + type + ' ' + numElement);
                    <%=ToHTML.getActivityInputEdit(type, null, numElement!!!!!)%>
                    return false;
                });
            }

            <c:forEach var="type1" items="<%=SectionType.values()%>">
            <jsp:useBean id="type1" type="com.igorole.basejava.webapp.model.SectionType"/>
            $('#add${type1.name()}').on('click', function () {
                $('#${type1.name()}Area').append("<%=ToHTML.getSectionInputTag(type1, null, true)%>");
                x();
            });
            </c:forEach>
            x();
        });
    </script>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container center-block">
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
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
                            <span class="input-group-addon" style="width: 200px">${type.title}</span>
                            <input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"
                                   class="form-control" placeholder="${type.title}" aria-describedby="basic-addon1">
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="container center-block">
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <jsp:useBean id="type" type="com.igorole.basejava.webapp.model.SectionType"/>
                    <div class="row page-header">
                        <div class="input-group-lg ">
                            <label class="col-xs-12 control-label">
                                    ${type.title}
                                <c:if test="${type!=SectionType.PERSONAL && type!=SectionType.OBJECTIVE}">
                                    <a href="" onclick="return false;" id="add${type.name()}">
                                        <i class="glyphicon glyphicon-plus-sign"></i>
                                    </a>
                                </c:if>
                                <c:if test="${type==SectionType.EDUCATION || type==SectionType.EXPERIENCE}">
                                    <input type='hidden' id='${type.name()}' name='counter_${type.name()}'
                                           value="0"> <!-- counter for EXPERIENCE and EDUCATION -->
                                </c:if>
                            </label>
                            <div id="${type.name()}Area" name="xxx">
                                <%=ToHTML.getSectionInputTag(type, resume.getSection(type), false)%>
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