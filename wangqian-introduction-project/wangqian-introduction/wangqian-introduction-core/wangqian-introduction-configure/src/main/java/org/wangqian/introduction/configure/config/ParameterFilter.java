package org.wangqian.introduction.configure.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wangqian.introduction.tools.utils.GetEnvironmentUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 参数过滤
 *
 * @author 王骞
 * @date 2019-11-06
 */
@Configuration
@Order(1)
public class ParameterFilter extends OncePerRequestFilter {

    @Autowired
    private GetEnvironmentUtil getEnvironmentUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.getParameterMap();
        ParameterRequestWrapper paramWrapper = new ParameterRequestWrapper(request, getEnvironmentUtil);
        filterChain.doFilter(paramWrapper, response);
    }
}
