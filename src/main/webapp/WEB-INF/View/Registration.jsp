<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org./TR/html4/loose.dtd">

<html>
<style type="text/css">
.errors{
color: red;
position:fixed;
text-align: left;
margin-left:30px;}
</style>
</head>
<body>

<form:form action="processRegistration" method="GET" modelAttribute="registrationDTO">
<div align="center">
<h3> Registration Here...!!</h3>
<p>
<label for="fn">Username </label>
<form:input type="text" id="fn" path="username" placeholder="Enter First Name"/>
<form:errors path="username" cssClass="errors"/>
</p>
<p>
<label for="p">Password </label>
<form:input type="password" id="p" path="password" placeholder="Enter Password"/>
<form:errors path="password" cssClass="errors"/>
</p>
<p>
<input type="submit" value="Registration"/>
</p>
</div>
</form:form>
<html>
</bod