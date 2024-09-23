 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
   <%@page contentType="text/html; charset=iso-8859-1"
      pageEncoding="iso-8859-1"%>

<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<body>
<img src="${pageContext.request.contextPath}/Image/${fileUpload}" height="500" width="1000">
<form action="Comment" method="post" modelAttribute="commentsDTO">
<h4 style="color:blue;">Enter Your Comment ${username}:</h4>
<TEXTAREA wrap="virtual" name="comment" rows=9 cols=90 MAXLENGTH=1000></TEXTAREA><BR>
<INPUT type="Submit" VALUE="Submit">
<INPUT type="Reset" VALUE="Clear">
</form>
</body>
</html>