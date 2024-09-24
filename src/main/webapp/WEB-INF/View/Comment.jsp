<html>
<body>

<p>
<div align="right">
<form action="logout" method="post">
    <input type="submit" value="Logout" />
</form>
</div>
</p>

<form action="ShowComments" method="POST" modelAttribute="commentsDTO">
<div align="center">
<h2> Hello ${username}</h2>
<h3 style="color:Blue;">Successfully Commented...</h3>
<p>
<input type="submit" value="ShowComments"/>
</p>
</div>
</body
</html>