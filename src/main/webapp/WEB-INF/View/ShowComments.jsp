 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<p>
<div align="right">
<form action="logout" method="post">
    <input type="submit" value="Logout" />
</form>
</div>
</p>

<div align="center">
<p>
<h1> Comment Section</h1>
</p>
<c:forEach var="Comment" items="${commentsDTOS}">
${Comment}
<br/>
</c:forEach>
<div align="center">
<form action="AddNoteSheet" method="POST">
<input type="submit" value="Add Note Sheet"/>
</form>
</div>
</div>
</body>
</html>