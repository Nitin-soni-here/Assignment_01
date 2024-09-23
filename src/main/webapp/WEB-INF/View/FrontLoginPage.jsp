 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

</div>
<div align="center">
<c:if test="${param.error!=null}">
<i style="color: red;"> <h3>Invalid Login And Password</h3></i>
</c:if>

<c:if test="${param.logout!=null}">
<i style="color: Blue;"> <h3>Successfully Logout</h3></i>
</c:if>
</div>

<div align="right">
<a href="/Assignment/home/Registration"><h3>Registration</h3></a>
</div>

<form:form method="POST" >
<div align="center">
<h3> Login Here...!!</h3>
<p>
<label for="ei"> Username: </label>
<input type="text" id="ei" name="username" placeholder="Enter Username"/>
<form:errors path="username" cssClass="errors"/>
</p>
<p>
<label for="p">Password :</label>
<input type="password" id="p" name="password" placeholder="Enter Password"/>
<form:errors path="password" cssClass="errors"/>
</p>
<p>
<input type="submit" value="Login"/>
</p>
</div>
</form:form>
<html>
</body>