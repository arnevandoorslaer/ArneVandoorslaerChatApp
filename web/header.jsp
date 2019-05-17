<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header" role="banner">
    <img class="img-thumbnail" alt="Books" src="images/books.jpg">
    <h1 class="page-header"><span>Chat App</span></h1>
    <nav class="nav nav-justified">
        <ul>
            <c:if test="${param.title=='Home'}">
                <li id="actual"><a href="Controller">Home</a></li>
                <li><a href="Controller?action=Chat">Chat</a></li>
            </c:if>
            <c:if test="${param.title=='Chat'}">
                <li><a href="Controller">Home</a></li>
                <li id="actual"><a href="Controller?action=Chat">Chat</a></li>
            </c:if>
        </ul>
    </nav>
    <h2 class="page-header">
        ${param.title}
    </h2>

</header>