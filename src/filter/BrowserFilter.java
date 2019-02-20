package filter;
/**
 * ???????????
 */
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BrowserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String userAgent = request.getHeader("user-agent");
		if (userAgent.contains("Firefox")) {
			chain.doFilter(request, response);
		} else if (userAgent.contains("Chrome")) {
			chain.doFilter(request, response);
		} else if (userAgent.contains(".NET")) {
			chain.doFilter(request, response);
		} else {
		}

	}

	@Override
	public void destroy() {

	}

}
