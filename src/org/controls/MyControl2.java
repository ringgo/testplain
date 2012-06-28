package org.controls;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.beans.User;
import org.daos.Dao;
import org.ext.dbutil.DbFactory;
import org.msf.annotations.ControlMapping;
import org.msf.annotations.ControlMarked;
import org.msf.web.CT;

@ControlMarked(path = "/report/2", isSingleton = false)
public class MyControl2 {
	public int i = 0;

	// http://localhost:8080/testmyrest/report/2/test/singleton
	@ControlMapping(path = "/test/singleton")
	public int testIsSingleton() {
		// 默认是单例模式，所以i会一直增加,如果是非单例模式:ControlMarked配置isSingleton=false
		// 则每次访问i都是是0
		return i++;
	}

	// http://localhost:8080/testmyrest/report/2/wyl/1
	@ControlMapping(path = "/wyl/{x}")
	public Object testx(int x) throws FileNotFoundException, Exception {
		if (x == 0)
			return CT.get().forward("/index.jsp");
		return CT.get().downloadfile("d:/test.jar", "中午.jar");
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
	@ControlMapping(path = "/{user}/{id}")
	public Object test(String user, int id) {
		System.out.println("path1..._");
		User u = CT.get().params2bean(User.class);
		if (u != null) {
			System.out.println("path1启动执行..." + u.getName() + " " + user + " "
					+ CT.get().reqParam("age"));
			CT.get().reqAttr("rpar", "从服务器响应的参数");
		} else
			System.out.println("path1启动执行...");
		return CT.get().forward("/index.jsp");
	}

	// http://localhost:8080/testmyrest/report/2/dbx/xx/654321
	@ControlMapping(path = "/dbx/xx/{idd}")
	public String getHtml1(String idd) {
		System.out.println("path2...+_");
		System.out.println("path2..." + idd);
		return "xxx";
	}

	// http://localhost:8080/testmyrest/report/2/dbx/xxx/654321
	@ControlMapping(path = "/{xxd}/xxx/654321")
	public void getHtml2(String xxd) {
		try {
			System.out.println("path3...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// http://localhost:8080/testmyrest/report/2/v/1/654321
	@ControlMapping(path = "/{named}/{xh}/654321")
	public Object getHtml0(String named, String xh) {
		try {
			System.out.println("path4..." + xh);

			CT.get().reqAttr("page_array", Dao.getUserList());
			CT.get().reqAttr("page_bd", "page++++bd");
			CT.get().reqAttr("page_hd", "page___bd");
		} catch (Exception e) {
			// param.getHttpResponse().setStatus(500);
			// return null;
			// return param.forward("/500.jsp");
			// e.printStackTrace();
		}
		return CT.get().forward("/vm/user/profile" + xh + ".vm");
	}

	// http://localhost:8080/testmyrest/report/2/dd/xx?json={id=1,name='lsf',sex=2,age=22,career='wt'}
	@ControlMapping(path = "/dd/xx")
	public void testjson() {
		User u = CT.get().jsonstr2bean(User.class, CT.get().reqParam("json"));
		System.out.println(u.getName() + " " + u.getCareer());
	}

	// http://localhost:8080/testmyrest/report/2/dd/re
	@ControlMapping(path = "/dd/re")
	public Object testre() {
		try {
			return CT.get().redirect("/report/dd/vv", "x", "作为xdx", "tstatt",
					"作为11");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// http://localhost:8080/testmyrest/report/2/dd/for
	@ControlMapping(path = "/dd/for")
	public Object testfor() {
		ArrayList arr = new ArrayList();
		arr.add(1);
		arr.add("str");
		return CT.get().forward("/for.jsp", "xm", "李师傅", "sex", false, "age",
				arr);
	}

	// http://localhost:8080/testmyrest/report/2/dd/vv?x=1132
	@ControlMapping(path = "/dd/vv")
	public Object testjson2() {
		try {
			System.out.println(CT.get().reqParam("x"));
			System.out.println(CT.get().reqParam("tstatt"));
			System.out.println(CT.get().reqAttr("x"));
		} catch (Exception x) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		User u = new User();
		u.setName(CT.get().reqParam("x"));
		u.setAge(11);
		u.setCareer("工程师");
		u.setSex("男");
		return CT.get().bean2jsonstr(u);
	}
	// 500
	// http://localhost:8080/testmyrest/report/2/dbx/xxxx/654321
}
