<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body class="container" onload="openSocket();">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if> <c:choose>
    <c:when test="${user!=null}">
        <p>Welcome ${user.getFirstName()}!</p>
        <form method="post" action="Controller?action=LogOut">
            <p>
                <input type="submit" id="logoutbutton" value="Log Out" onclick="openSocket()">
            </p>
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" action="Controller?action=Register" class="login">
            <p>
                <label for="email">Jouw email </label>
                <input type="email" id="email" name="email">
            </p>
            <p>
                <label for="firstname">Jouw voornaam </label>
                <input type="text" id="firstname" name="firstname">
            </p>
            <p>
                <label for="lastname">Jouw familienaam </label>
                <input type="text" id="lastname" name="lastname">
            </p>
            <p>
                <label for="age">Jouw leeftijd</label>
                <input type="number" id="age" name="age">
            </p>
            <p>
                <label for="gender">Jouw geslacht</label>
                <input type="text" id="gender" name="gender">
            </p>
            <p>
                <label for="password">password</label>
                <input type="password" id="password" name="password">
            </p>
            <p>
                <label for="confirm_password">Confirm password</label>
                <input type="password" id="confirm_password" name="confirm_password">
            </p>
            <p>
                <input class="btn btn-info" type="submit" id="registerbutton" value="Create Account">
            </p>
        </form>
    </c:otherwise>
</c:choose></main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script type="text/javascript" src="js/passwordvalidation.js"></script>
</body>
</html>
