<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat App"/>
</jsp:include>
<body class="container" onload="ready()">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <div class="chatApp">
        <div class="text-right" id="stateDiv"></div>
        <table class="table-sm table-striped table-bordered table-hover" id="friendlist"></table>

        <label for="stateText">Change State</label>
        <input type="text" id="stateText">
        <input type="submit" id="statebutton" value="Change">
<br>
        <label for="friendText">Add Friend</label>
        <input type="text" id="friendText">
        <input type="submit" id="friendbutton" value="Add">
    </div>
    <div id="messages"></div>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/chat.js"></script>
</body>
</html>