<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org./TR/html4/loose.dtd">

<html>
<body>
<p>
<div align="right">
<form action="logout" method="post">
    <input type="submit" value="Logout" />
</form>
</div>
</p>


<form:form action="UploadImage" method="POST" modelAttribute="parts" >
<div align="center">

<p>
<h3> Hello, ${userName}</h3>
<p>
<label for="f"> File Upload Here...!!</label>
</p>
<form:input type="file" id="f" path="profile" />
<p>
<input type="submit" value="submit"/>
</p>
</div>
</form:form>
</body>
</html>