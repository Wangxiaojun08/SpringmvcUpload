<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>

<form action="testMvc" method="POST">
	value: <input type="text" name="name">
	submit: <input type="submit" value="submit">
</form>

<%System.out.println(this.getClass().getClassLoader());
int a = 3/2;
%>
</body>
</html>
