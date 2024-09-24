<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org./TR/html4/loose.dtd">

<html>
<body>
<form:form action="UploadImage" method="POST" enctype="multipart/form-data">
<div align="center">

<p>
<div align="right">
<form action="logout" method="post">
    <input type="submit" value="Logout" />
</form>
</div>
</p>

<p>
<h3> Hello, ${userName}</h3>
<p>
<label for="f"> File Upload Here...!!</label>
</p>
<input type="file" id="f" name="profile" />
<p>
<input type="submit" value="submit"/>
</p>
</div>
</form:form>
</body>
</html>