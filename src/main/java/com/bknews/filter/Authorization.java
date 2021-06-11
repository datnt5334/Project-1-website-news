package com.bknews.filter;

import com.bknews.constant.SystemConstant;
import com.bknews.model.UserModel;
import com.bknews.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorization implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith("/admin")) {
            UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if (model != null) {
                if (model.getRole().getCode().equals(SystemConstant.ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (model.getRole().getCode().equals(SystemConstant.USER)) {
                    response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&"
                    + "message=not_permission");
                }
            } else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&"
                + "message=not_login");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
