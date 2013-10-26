package org.filters;

import org.msf.mvc.annotations.InterceptorMarked;
import org.msf.mvc.web.CT;
import org.msf.mvc.web.filter.Filter;
import org.msf.mvc.web.filter.FilterChain;

@InterceptorMarked(path = "/action/.*", order = 1)
public class LoginFilter implements Filter {

	@Override
	public void doFilter(FilterChain chain) throws Exception {
		System.out.println("LoginFilter..........");

		if ("1".equals(CT.getReqParam("u.sex")))
			chain.forward("/test.jsp", "a", "--测试--");
		else if ("5".equals(CT.getReqParam("u.sex")))
			chain.string("直接返回字符串");
		else if ("7".equals(CT.getReqParam("u.sex")))
			chain.redirect("/test.jsp");
		else
			chain.doFilter();
	}

}
