<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<body>
		This is my JSP page!!!!!!!!.
		<br>
		${rpar}
		<%request.setAttribute("x","tstatt--1110"); %>
		<jsp:forward page="/report/dd/vv?x=1132&tstatt=sdh"></jsp:forward>
	</body>
</html>
