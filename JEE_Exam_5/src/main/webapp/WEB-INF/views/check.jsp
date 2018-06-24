<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String param1 = (String) session.getAttribute("param1");
String param2 = (String) session.getAttribute("param2");
%>

<p>Parametry z sesji : ${param1}, ${param2}</p>

</body>
</html>