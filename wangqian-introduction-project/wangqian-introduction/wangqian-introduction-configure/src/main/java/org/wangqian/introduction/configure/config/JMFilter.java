package org.wangqian.introduction.configure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤
 *
 * @author 王骞
 * @date 2019-11-06
 */
@WebFilter(asyncSupported = true)
public class JMFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //LOGGER.info("进入过滤器");

        //放过请求
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
