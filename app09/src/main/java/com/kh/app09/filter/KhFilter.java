package com.kh.app09.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class KhFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("KhFilter.doFilter 호출 됨");
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
