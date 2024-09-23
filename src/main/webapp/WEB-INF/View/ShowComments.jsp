 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div align="center">
<p>
<h1> Comment Section</h1>
</p>
<c:forEach var="Comment" items="${commentsDTOS}">
${username}:${Comment}
<br/>
</c:forEach>
</div>
</body>
</html>