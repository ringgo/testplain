package org.filters;

import org.msf.mvc.annotations.InterceptorMarked;
import org.msf.mvc.web.filter.Filter;
import org.msf.mvc.web.filter.FilterChain;

@InterceptorMarked(path = "/action/test2/.*", order = 2)
public class EncodeFilter implements Filter {

	@Override
	public void doFilter(FilterChain chain) throws Exception {
		System.out.println("EncodeIntercept..........");
		chain.doFilter();
	}

}
