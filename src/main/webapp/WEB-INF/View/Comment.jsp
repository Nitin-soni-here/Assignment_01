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
</form>
<form action="Save" method="post">
<input type="Submit" Value="Comment More">
</form>
</div>
<div align="center">
<form action="AddNoteSheet" method="POST">
<input type="submit" value="Add Note Sheet"/>
</form>
</div>
</body
</html>