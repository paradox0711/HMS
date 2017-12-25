package global;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter(
		filterName = "authorityFilter",
		urlPatterns = { "/*" },
		initParams = {
				@WebInitParam(name = "encoding", value = "utf-8"),
				@WebInitParam(name = "loginPage", value = "login.jsp"),
				@WebInitParam(name = "homePage", value = "login.jsp"),
				@WebInitParam(name = "managerPage", value = "login.jsp"),
				@WebInitParam(name = "receptionistPage", value = "login.jsp")
		})
public class AuthorityFilter implements javax.servlet.Filter {
	private FilterConfig config; 
    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see AuthorityFilter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
	}

	/**
	 * @see AuthorityFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see AuthorityFilter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
}
