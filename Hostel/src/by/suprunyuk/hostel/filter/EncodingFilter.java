package by.suprunyuk.hostel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter for setting UTF-8 as request encoding  
 * 
 * @author Anton Suprunyuk
 */
public class EncodingFilter implements Filter {

	/** 
	 * String indentifier for encoding utf-8
	 */
	private static final String ENCODING_UTF8 = "UTF-8";
	
	/**
	 * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	/**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service. This method is only called once all threads within
     * the filter's doFilter method have exited or after a timeout period has
     * passed. After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(ENCODING_UTF8);
		chain.doFilter(request, response);
	}

	/**
     * This method gives the filter an opportunity to clean up any resources
     * that are being held (for example, memory, file handles, threads) and make
     * sure that any persistent state is synchronized with the filter's current
     * state in memory.
	 */
	@Override
	public void destroy() {
	}

}
