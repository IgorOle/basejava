<%@ page import="com.igorole.basejava.webapp.model.ContactType" %>
<%@ page import="com.igorole.basejava.webapp.view.ToHTML" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<a href="resume?action=add" name="newResume">adddd</a>
<div class="container">
    <table border="1" cellpadding="8" cellspacing="0"  class="table table-bordered table-striped">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.igorole.basejava.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td><%=ToHTML.getContact(resume, ContactType.MAIL)%></td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete"><i class="glyphicon glyphicon-trash"></i></a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=edit"><i class="glyphicon glyphicon-pencil"></i></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html></html>