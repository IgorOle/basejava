<%@ page import="com.igorole.basejava.webapp.view.ToHTML" %>
<%@ page import="com.igorole.basejava.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <jsp:useBean id="resume" type="com.igorole.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <div class="container">
        <div class="col-lg-12">

            <div class="row">
                <h2>${resume.fullName}&nbsp;
                    <a href="resume?uuid=${resume.uuid}&action=edit">
                        <i class="glyphicon glyphicon-edit"></i>
                    </a>
                </h2>
            </div>
            <div class="row">
                <c:forEach var="contactEntry" items="${resume.contacts}">
                    <jsp:useBean id="contactEntry"
                                 type="java.util.Map.Entry<com.igorole.basejava.webapp.model.ContactType, java.lang.String>"/>
                        <div class="row">
                            <div class="col-sm-2">
                                <%=contactEntry.getKey().getTitle()%>
                            </div>
                            <div class="col-sm-1">
                                <%=ToHTML.getContact(resume, contactEntry.getKey())%>
                            </div>
                        </div>
                </c:forEach>
                <hr>
            </div>
            <div row="row">
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <jsp:useBean id="type" type="com.igorole.basejava.webapp.model.SectionType"/>
                    <%=ToHTML.getSectionView(resume, type)%>
                </c:forEach>
            </div>

        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>