package ru.itis.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.itis.localization.Localizations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LocalizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private Localizations localizations;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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

        Map<String, String> locale = localizations.getLocalizationByLanguage(lang);

        request.setAttribute("locale", locale);

        if (!isPresent) {
            Cookie cookie = new Cookie("locale", lang);
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return true;
    }
}