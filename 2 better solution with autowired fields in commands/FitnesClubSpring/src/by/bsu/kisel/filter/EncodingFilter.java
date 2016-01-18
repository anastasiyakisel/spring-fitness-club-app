/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.filter;

import java.io.IOException;
import javax.servlet.*;

/**
 *
 * @author Anastasia Kisel
 */
public class EncodingFilter implements Filter{

    private final String encoding="utf-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
    
}
