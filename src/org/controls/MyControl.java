package org.controls;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.beans.User;
import org.daos.Dao;
import org.ext.dbutil.DbFactory;
import org.msf.annotations.ControlMapping;
import org.msf.annotations.ControlMarked;
import org.msf.web.CT;

@ControlMarked(path = "/report")
public class MyControl {
	public int i = 0;

	// http://localhost:8080/testmyrest/report/test/testMethod
	@ControlMapping(path = "/test/testMethod", httpMethod = "post")
	public String testMethod() {
		return "只支持post访问!";
	}

	// http://localhost:8080/testmyrest/report/test/fileupload
	@ControlMapping(path = "/test/fileupload")
	public String fileupload() {
		try {
			Map<String, String> upinfo = CT.get().uploadfile("e:/tmp",
					".txt,.sql,.doc,.rar", 10);
			if (upinfo == null)
				return "上传失败!";
			else {
				for (String key : upinfo.keySet()) {
					System.out.println("表单属性：" + key + " 表单属性值："
							+ upinfo.get(key));
				}
			}
			return "上传成功!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "上传失败!";
	}

	// http://localhost:8080/testmyrest/report/test/singleton
	@ControlMapping(path = "/test/singleton")
	public int testIsSingleton() {
		// 默认是单例模式，所以i会一直增加,如果是非单例模式:ControlMarked配置isSingleton=false
		// 则每次访问i都是是0
		return i++;
	}

	// http://localhost:8080/testmyrest/report/wyl/1
	@ControlMapping(path = "/wyl/{x}")
	public Object fileDownload(String x) {

		if (Integer.valueOf(x) == 0)
			return CT.get().forward("/index.jsp");
		try {
			return CT.get().downloadfile(
					"D:/projects/css/free/css tmplate/_music/hjbj.mp3",
					"hjbj.mp3");
		} catch (FileNotFoundException e) {
			return "文件不存在!";
		}
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
	@ControlMapping(path = "/{user}/{id}")
	public Object test(String user, String id) {
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

	// http://localhost:8080/testmyrest/report/dbx/xx/654321
	@ControlMapping(path = "/dbx/xx/{idd}")
	public String getHtml1(int idx) {
		System.out.println("path2...+_");
		System.out.println("path2..." + idx);
		return "xxx";
	}

	// http://localhost:8080/testmyrest/report/dbx/xxx/654321
	@ControlMapping(path = "/{xxd}/xxx/654321")
	public void getHtml2(String xxd) {
		try {
			System.out.println("path3...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// http://localhost:8080/testmyrest/report/v/1/654321
	@ControlMapping(path = "/{named}/{xh}/654321")
	public Object getHtml0(String named, int xh) {
		try {
			System.out.println("path4..." + xh);

			// CT.get().reqAttr("page_array", Dao.getUserList());
			CT.get().reqAttr("page_bd", "page+++作为+bd");
			CT.get().reqAttr("page_hd", "page___bd");
		} catch (Exception e) {
			// param.getHttpResponse().setStatus(500);
			// return null;
			// return param.forward("/500.jsp");
			// e.printStackTrace();
		}
		return CT.get().forward("/vm/user/profile" + xh + ".vm", "page_bd3",
				"page+++作为3d+bd");
	}

	// http://localhost:8080/testmyrest/report/dd/xx?json={id=1,name='lsf',sex=2,age=22,career='wt'}
	@ControlMapping(path = "/dd/xx")
	public void testjson() {
		User u = CT.get().jsonstr2bean(User.class, CT.get().reqParam("json"));
		System.out.println(u.getName() + " " + u.getCareer());
	}

	// http://localhost:8080/testmyrest/report/dd/re
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

	// http://localhost:8080/testmyrest/report/dd/for
	@ControlMapping(path = "/dd/for")
	public Object testfor() {
		ArrayList arr = new ArrayList();
		arr.add(1);
		arr.add("str");
		return CT.get().forward("/for.jsp", "xm", "李师傅", "sex", false, "age",
				arr);
	}

	// http://localhost:8080/testmyrest/report/dd/vv?x=1132
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

	// http://localhost:8080/testmyrest/report/testsql/user
	@ControlMapping(path = "/testsql/user")
	public void test() {
		try {
			List<User> users = Dao.getUserList();
			for (User u : users) {
				System.out.println(u.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 500
	// http://localhost:8080/testmyrest/report/dbx/xxxx/654321
}
