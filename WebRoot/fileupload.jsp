<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<body>
		<form action="/testmyrest/report/test/fileupload"
			enctype="multipart/form-data" method="post">
			<input id="mf1" name="mf1" type="file" /><br/>
			<input id="mf2" name="mf2" type="file" /><br/>
			<input id="mf3" name="mf3" type="file" /><br/>
			<input id="mf4" name="mf4" type="file" /><br/>
			<input id="mft" name="mft" value="测试.文本.test" /><br/>
			<input type="submit" value="上传" />
		</form>
	</body>
</html>
