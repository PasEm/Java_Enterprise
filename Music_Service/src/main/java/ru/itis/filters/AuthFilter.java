package ru.itis.filters;

import ru.itis.services.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/profile/ftl", "/my_collection/ftl"})
public class AuthFilter implements Filter {
    private LoginService loginService;

    @Override
    public void init(FilterConfig filterConfig) {
        loginService = (LoginService) filterConfig.getServletContext().getAttribute("loginService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (loginService.isExistsByCookie(cookie.getValue())) {
                        filterChain.doFilter(request, response);
                        return;
                    }
                }
            }

        }
        response.sendRedirect("/signIn/ftl");
    }

    @Override
    public void destroy() {

    }
}