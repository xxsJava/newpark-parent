package com.newpark.core.filter;


import com.newpark.core.utils.LoginHelper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 入口过滤器。
 *
 * @author jack
 * @date 2023/3/14
 */
@Component
@WebFilter(filterName = "firstEntryFilter", urlPatterns = "/*")
@Order(1)
public class FirstEntryFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //清理threadLocal
        LoginHelper.clearThreadLocal();
        //调用下一个过滤器链
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
