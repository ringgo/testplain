<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp</title>
</head>
<body>
  <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/action/test5/1/2" method="post">
	 <input type="text" name="u.uname"/><br/>
	 <input type="password" name="u.upwd"/><br/>
	 男：<input type="checkbox" name="u.sex" value="1"/> 女：<input type="checkbox" name="u.sex" value="2"/><br/>
	 文件1<input type="file" name="u.f1"/><br/>
	 文件2<input type="file" name="u.f2"/><br/>
	 <img style="cursor:pointer;" id="_imgcode" src="${pageContext.request.contextPath}/action/test6.jpeg"/>
	 <input type="submit" value="提交"/>
  </form>
  -->> ${rs}
  -<> ${params.tbnm}
</body>
</html>