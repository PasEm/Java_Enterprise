package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.services.LoginService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signIn/ftl")
public class SignInServlet extends HttpServlet {
    private LoginService loginService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        loginService = (LoginService) context.getAttribute("loginService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/ftl/signIn.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<String> optionalCookieValue = loginService.login(login, password);

        if (optionalCookieValue.isPresent()) {
            Cookie auth = new Cookie("auth", optionalCookieValue.get());
            auth.setPath("/");
            response.addCookie(auth);
            response.setStatus(201);
            response.sendRedirect("/main/ftl");
        } else {
            response.sendRedirect("/signIn/ftl");
        }
    }
}