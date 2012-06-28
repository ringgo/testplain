<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<body>
		<form action="/testmyrest/report/test/fileupload"
			enctype="multipart/form-data" method="post">
			<input id="mf1" name="mf1" type="file" /><br/>
			<input id="mf2" name="mf2" type="file" /><br/>
			<input id="mf3" name="mf3" type="file" /><br/>
			<input id="mf4" name="mf4" type="file" /><br/>
			<input id="mft" name="mft" value="测试.文本.xxx123" /><br/>
			<input type="submit" value="上传" />
		</form>
		<embed height="27px" width="100%" name="plugin" src="google-audio-player.swf?audioUrl=http://localhost:8080/testmyrest/report/wyl/1" type="application/x-shockwave-flash"/>
	</body>
</html>
