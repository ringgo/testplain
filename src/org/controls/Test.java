package org.controls;

import java.io.IOException;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.config.FactoryConfiguration;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.beans.User;
import org.commons.AuthImg;
import org.msf.db.dbpool.DbFactory;
import org.msf.db.dbutils.QH;
import org.msf.mvc.annotations.ControlMapping;
import org.msf.mvc.annotations.ControlMarked;
import org.msf.mvc.annotations.FileUpload;
import org.msf.mvc.utils.RestUtil;
import org.msf.mvc.web.CT;
import org.msf.mvc.web.ControlRender;

@ControlMarked(path = "/action")
public class Test {
	@ControlMapping(isload = true)
	public static void start() {
		System.out.println("start...");
		DbFactory.start();
	}

	@ControlMapping(isdestroy = true)
	public static void destroy() {
		System.out.println("destroy...");
		DbFactory.shutdown();
	}

	@ControlMapping(path = "/test1")
	public static String test1() {
		return "test1...";
	}

	@ControlMapping(path = "/test2/{name}")
	public static String test2(String name) {
		return "test2..." + name;
	}

	@ControlMapping(path = "/test3/{a}/{b}")
	public static String test3(int a, int b) {
		return "test3..." + (a + b);
	}

	@ControlMapping(path = "/test4/{a}/{b}")
	public static ControlRender test4(int a, int b) {
		return CT.forward("/test.jsp", "a", (a + b));
	}

	@ControlMapping(path = "/test5/{a}/{b}")
	@FileUpload(fileDir = "H:/temp/_upfiles", extension = ".txt,.java,.css", maxsize = 20)
	public static ControlRender test5(int a, int b) {
		String uname = CT.getReqParam("u.uname");
		// String upwd = CT.getReqParam("upwd");
		User u = CT.params2bean("u", User.class);
		User selu = null;
		selu = QH.query("mysql5", User.class,
				"select * from act_id_user where ID_=?", uname);
		return CT.forward("/form.jsp", "rs", (u + ":" + (a + b) + selu));
	}

	@ControlMapping(path = "/test6.jpeg", contentType = "image/jpeg")
	public static void test6() {
		try {
			AuthImg.buildImageCode();
		} catch (Exception e) {
		}
	}

	// 动态sql语句测试(velocity模板act_id_user)
	@ControlMapping(path = "/test7")
	public static String test7() {
		User u = new User();
		u.setUname("测试姓名!!!");
		CT.setReqAttr("u", u);

		String dsql = "select * from ${params.tbnm} where 1=? and 1='${u.uname}' and 1=$link.cpage('p') #if($link.cpage('p')==2) xxx #end";
		QH.query("mysql5", User.class, dsql, 1);
		return "test7..." + dsql;
	}

	public static void main(String[] args) {
		try {
			XmlFactoryConfiguration cfg = new XmlFactoryConfiguration(true);
			cfg.read("F:/_my/workspaces/e_workspaces1/demo/WebRoot/WEB-INF/tools.xml");
			ToolboxFactory factory = cfg.createFactory();
//			tbf.createToolbox(Scope.REQUEST);
 			System.out.println(factory);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(RestUtil.matches("/action/test2/([.]{1}[\\w%-]+)",
//				"/action/test2/ss.sgif"));
	}
}
