package com.thevolume360.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse hsr = (HttpServletResponse) response;
        hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        hsr.setDateHeader("Expires", 0); // Proxies.
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
