package org.tools;

import org.apache.velocity.tools.view.LinkTool;
import org.msf.mvc.utils.RestUtil;
import org.msf.mvc.web.CT;

/**
 * velocity工具类
 * 
 * @author lisf
 * 
 */
public class Link extends LinkTool {
	private static final String CSS_DIR = "/css/";
	private static final String JS_DIR = "/js/";
	private static final String SWF_DIR = "/swf/";
	private static final String IMG_DIR = "/images/";
	public static final String REQ_URL = "REQ_URL";

	// 移除attr值
	public static final void removeAttr(String attr) {
		CT.request().removeAttribute("_id");
	}

	// 获得ContextPath
	public static final String cpath() {
		return CT.getContextPath();
	}

	// 获得参数值
	public static final String param(String pnm) {
		String rpara = CT.getReqParam(pnm);
		return rpara == null ? "" : rpara;
	}

	// 获得参数值并且过滤
	public static final String fparam(String pnm) {
		String rpara = RestUtil.htmlspecialchars(param(pnm));
		return rpara == null ? "" : rpara;
	}

	// 获得当前第几页
	public static final int cpage(String pnm) {
		try {
			return Integer.parseInt(param(pnm));
		} catch (Exception x) {
		} finally {
		}
		return 1;
	}

	// 供分页使用,动态拼接地址,pnum:转到第几页
	public static final String go(long pnum) {
		String qstr = CT.request().getQueryString();
		String rs = qstr == null ? "" : qstr.replaceAll("&?p=\\d*", "");
		return ("".equals(rs) ? "?p=" + pnum : CT.getReqAttr(REQ_URL)
				.toString() + "?" + rs + "&p=" + pnum);
	}

	// 请求action,acturl:如/u;/t
	public static final String action(String acturl) {
		return cpath() + acturl;
	}

	// 获取css文件
	public static final String css(String css, String version) {
		return String.format("%s%s%s?v=%s", cpath(),
				(css != null && css.startsWith(CSS_DIR)) ? "" : CSS_DIR, css,
				version);
	}

	// 获取js文件
	public static final String js(String js, String version) {
		return String.format("%s%s%s?v=%s", cpath(),
				(js != null && js.startsWith(JS_DIR)) ? "" : JS_DIR, js,
				version);
	}

	// 获取swf文件
	public static final String swf(String swf, String version) {
		return String.format("%s%s%s?v=%s", cpath(),
				(swf != null && swf.startsWith(SWF_DIR)) ? "" : SWF_DIR, swf,
				version);
	}

	public static final String swf(String swf) {
		return String.format("%s%s%s", cpath(),
				(swf != null && swf.startsWith(SWF_DIR)) ? "" : SWF_DIR, swf);
	}

	// 获取image文件
	public static final String img(String img, String version) {
		return String.format("%s%s%s?v=%s", cpath(),
				(img != null && img.startsWith(IMG_DIR)) ? "" : IMG_DIR, img,
				version);
	}

	public static final String img(String img) {
		return String.format("%s%s%s", cpath(),
				(img != null && img.startsWith(IMG_DIR)) ? "" : IMG_DIR, img);
	}
}
