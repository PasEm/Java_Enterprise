package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter("*")
public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String lang = request.getParameter("lang");
        boolean isPresent = false;

        if (request.getCookies() == null) {
            lang = lang == null ? "En" : lang;
            isPresent = true;

            Cookie cookie = new Cookie("locale", lang);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("locale")) {
                    lang = (lang == null) ? (cookie.getValue() == null) ? "En" : cookie.getValue() : lang;
                    cookie.setValue(lang);
                    break;
                }
            }
            if (lang == null) {
                lang = "En";
            }
        }

        Map<String, String> locale = (Map<String, String>) request.getServletContext().getAttribute("locale" + lang);
        request.setAttribute("locale", locale);

        if (!isPresent) {
            Cookie cookie = new Cookie("locale", lang);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
