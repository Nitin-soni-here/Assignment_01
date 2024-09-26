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


<style>
body {
    font-family: Arial, sans-serif;
    background-color: #fff6f6;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}
.login-container {
    background: white;
    padding: 50px;
    border-radius: 50px;
    box-shadow: 0 0 50px rgba(6, 1, 5, 0.3);
}

.input-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 10px;
}

input {
    width: 100%;
    padding: 10px;
    border: 5px solid #ccc;
    border-radius: 4px;
}
button {
    width: 50%;
    padding: 10px;
    background-color: #007bff;
    border: 5px solid #ccc;
    color: white;
    border-radius: 4px;
    cursor: pointer;
}



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
<div class="login-container" align="center">
<h3 style="text-decoration:double underline;"> Login Here...!!</h3>

<div class="input-group">
<p>
<label for="ei"> Username: </label>
<input type="text" id="ei" name="username" placeholder="Enter Username" required>
<form:errors path="username" cssClass="errors"/>
</p>
</div>

<div class="input-group">
<p>
<label for="p">Password :</label>
<input type="password" id="p" name="password" placeholder="Enter Password"/>
<form:errors path="password" cssClass="errors"/>
</p>
</div>

<p>
<button type="submit">Login</button>
</p>
</div>
</form:form>

</body>
</html>