package org.controls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.beans.User;
import org.daos.Dao;
import org.ext.dbutil.DbFactory;
import org.msf.web.ControlMapping;
import org.msf.web.ControlMarked;
import org.msf.web.ControlParam;
import org.msf.web.ControlRender;

@ControlMarked(path = "/report")
public class MyControl {
	public static final String path1 = "/{user}/{id}";

	public static final String path2 = "/dbx/xx/{idd}";

	public static final String path3 = "/{xxd}/xxx/654321";

	public static final String path4 = "/{named}/{xh}/654321";

	// http://localhost:8080/testmyrest/report/wyl/1
	@ControlMapping(path = "/wyl/{wyl}")
	public Object testx(ControlParam params) throws FileNotFoundException,
			Exception {
		int x = Integer.valueOf(params.getPathParam("wyl"));
		if (x == 0)
			return new ControlRender("/index.jsp");
		return new ControlRender(new FileInputStream(new File("E:/xxx.txt")),
				"application/x-msdownload;", "中文.txt");
	}

	@ControlMapping(isload = true)
	public void init() {
		DbFactory.start();
	}

	@ControlMapping(isdestroy = true)
	public void destroy() {
		DbFactory.destroy();
	}

	// http://localhost:8080/testmyrest/report/dbx/654321?id=11&name=lsf&sex=1&age=22(注意不能带#)
	@ControlMapping(path = path1)
	public ControlRender test(ControlParam params) {
		System.out.println("path1..._");
		User u = params.params2bean(User.class);
		if (u != null) {
			System.out.println("path1启动执行..." + u.getName() + " "
					+ params.getPathParam("user") + " " + params.get("age"));
			params.set("rpar", "从服务器响应的参数");
		} else
			System.out.println("path1启动执行...");
		return new ControlRender("/index.jsp");
	}

	// http://localhost:8080/testmyrest/report/dbx/xx/654321
	@ControlMapping(path = path2)
	public String getHtml1(ControlParam params) {
		System.out.println("path2...+_");
		System.out.println("path2..." + params.getPathParam("idd"));
		return "xxx";
	}

	// http://localhost:8080/testmyrest/report/dbx/xxx/654321
	@ControlMapping(path = path3)
	public void getHtml2(ControlParam params) {
		try {
			System.out.println("path3...");
			if (params != null) {
				HttpServletRequest req = params.getHttpRequest();
				params.getHttpResponse().getWriter().write(
						"dbx2....." + req.getRequestURI());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// http://localhost:8080/testmyrest/report/v/1/654321
	@ControlMapping(path = path4)
	public ControlRender getHtml0(ControlParam param) {
		try {
			System.out.println("path4..." + param.getPathParam("xh"));

			param.set("page_array", Dao.getUserList());
			param.set("page_bd", "page++++bd");
			param.set("page_hd", "page___bd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ControlRender("/vm/user/profile" + param.getPathParam("xh")
				+ ".vm");
	}

	// http://localhost:8080/testmyrest/report/dd/xx?json={id=1,name='lsf',sex=2,age=22,career='wt'}
	@ControlMapping(path = "/dd/xx")
	public void testjson(ControlParam params) {
		User u = params.jsonstr2bean(User.class, params.get("json"));
		System.out.println(u.getName() + " " + u.getCareer());
	}

	// http://localhost:8080/testmyrest/report/dd/vv
	@ControlMapping(path = "/dd/vv")
	public String testjson2(ControlParam params) {
		User u = new User();
		u.setName("李师傅");
		u.setAge(11);
		u.setCareer("工程师");
		u.setSex("男");
		return params.bean2jsonstr(u);
	}
}
