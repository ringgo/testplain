package org.controls;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.beans.User;
import org.daos.Dao;
import org.ext.dbutil.DbFactory;
import org.msf.annotations.ControlMapping;
import org.msf.annotations.ControlMarked;
import org.msf.web.ControlRender;
import org.msf.web.ControlTool;

@ControlMarked(path = "/report/2", isSingleton = false)
public class MyControl2 {
	public static final String path1 = "/{user}/{id}";

	public static final String path2 = "/dbx/xx/{idd}";

	public static final String path3 = "/{xxd}/xxx/654321";

	public static final String path4 = "/{named}/{xh}/654321";

	public int i = 0;

	// http://localhost:8080/testmyrest/report/2/test/singleton
	@ControlMapping(path = "/test/singleton")
	public int testIsSingleton() {
		// 默认是单例模式，所以i会一直增加,如果是非单例模式:ControlMarked配置isSingleton=false
		// 则每次访问i都是是0
		return i++;
	}

	// http://localhost:8080/testmyrest/report/2/wyl/1
	@ControlMapping(path = "/wyl/{wyl}")
	public Object testx(ControlTool t) throws FileNotFoundException, Exception {
		int x = Integer.valueOf(t.getX("wyl"));
		if (x == 0)
			return t.forward("/index.jsp");
		return t.downloadFile("d:/test.jar", "中午.jar");
	}

	@ControlMapping(isload = true)
	public void init() {
		DbFactory.start();
	}

	@ControlMapping(isdestroy = true)
	public void destroy() {
		DbFactory.destroy();
	}

	// http://localhost:8080/testmyrest/report/2/dbx/654321?id=11&name=lsf&sex=1&age=22(注意不能带#)
	@ControlMapping(path = path1)
	public ControlRender test(ControlTool t) {
		System.out.println("path1..._");
		User u = t.params2bean(User.class);
		if (u != null) {
			System.out.println("path1启动执行..." + u.getName() + " "
					+ t.getX("user") + " " + t.get("age"));
			t.set("rpar", "从服务器响应的参数");
		} else
			System.out.println("path1启动执行...");
		return t.forward("/index.jsp");
	}

	// http://localhost:8080/testmyrest/report/2/dbx/xx/654321
	@ControlMapping(path = path2)
	public String getHtml1(ControlTool t) {
		System.out.println("path2...+_");
		System.out.println("path2..." + t.getX("idd"));
		return "xxx";
	}

	// http://localhost:8080/testmyrest/report/2/dbx/xxx/654321
	@ControlMapping(path = path3)
	public void getHtml2(ControlTool t) {
		try {
			System.out.println("path3...");
			if (t != null) {
				HttpServletRequest req = t.getHttpRequest();
				t.getHttpResponse().getWriter().write(
						"dbx2....." + req.getRequestURI());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// http://localhost:8080/testmyrest/report/2/v/1/654321
	@ControlMapping(path = path4)
	public ControlRender getHtml0(ControlTool t) {
		try {
			System.out.println("path4..." + t.getX("xh"));

			t.set("page_array", Dao.getUserList());
			t.set("page_bd", "page++++bd");
			t.set("page_hd", "page___bd");
		} catch (Exception e) {
			// param.getHttpResponse().setStatus(500);
			// return null;
			// return param.forward("/500.jsp");
			// e.printStackTrace();
		}
		return t.forward("/vm/user/profile" + t.getX("xh") + ".vm");
	}

	// http://localhost:8080/testmyrest/report/2/dd/xx?json={id=1,name='lsf',sex=2,age=22,career='wt'}
	@ControlMapping(path = "/dd/xx")
	public void testjson(ControlTool t) {
		User u = t.jsonstr2bean(User.class, t.get("json"));
		System.out.println(u.getName() + " " + u.getCareer());
	}

	// http://localhost:8080/testmyrest/report/2/dd/re
	@ControlMapping(path = "/dd/re")
	public Object testre(ControlTool t) {
		try {
			return t.redirect("/report/dd/vv", "x", "作为xdx", "tstatt", "作为11");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// http://localhost:8080/testmyrest/report/2/dd/for
	@ControlMapping(path = "/dd/for")
	public Object testfor(ControlTool t) {
		ArrayList arr = new ArrayList();
		arr.add(1);
		arr.add("str");
		return t.forward("/for.jsp", "xm", "李师傅", "sex", false, "age", arr);
	}

	// http://localhost:8080/testmyrest/report/2/dd/vv?x=1132
	@ControlMapping(path = "/dd/vv")
	public Object testjson2(ControlTool t) {
		try {
			System.out.println(t.get("x"));
			System.out.println(t.get("tstatt"));
			System.out.println(t.getAtt("x"));
		} catch (Exception x) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		User u = new User();
		u.setName(t.get("x"));
		u.setAge(11);
		u.setCareer("工程师");
		u.setSex("男");
		return t.bean2jsonstr(u);
	}
	// 500
	// http://localhost:8080/testmyrest/report/2/dbx/xxxx/654321
}
