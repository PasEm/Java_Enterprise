package ru.itis.controllers.servlets;

import lombok.SneakyThrows;
import ru.itis.forms.UserForm;
import ru.itis.services.LoginService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/signUp/ftl")
public class SignUpServlet extends HttpServlet {

    private UserService userService;
    private LoginService loginService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        loginService = (LoginService) context.getAttribute("loginService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/ftl/signUp.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String surname = request.getParameter("surname");
        String firstName = request.getParameter("first_name");
        String country = request.getParameter("country");
        LocalDate birthdate = request.getParameter("date").equals("") ? null : Date.valueOf(request.getParameter("date")).toLocalDate();
        String login = request.getParameter("login");

        UserForm userForm = UserForm.builder()
                .email(email)
                .password(password)
                .firstName(firstName.equals("") ? null : firstName)
                .surname(surname.equals("") ? null : surname)
                .login(login)
                .country(country.equals("") ? null : country)
                .birthdate(birthdate)
                .build();
        userService.addUser(userForm);

        Optional<String> optionalCookieValue = loginService.login(login, password);

        if (optionalCookieValue.isPresent()) {
            Cookie cookie = new Cookie("auth", optionalCookieValue.get());
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.setStatus(201);
            response.sendRedirect("/main/ftl");
        } else {
            response.setStatus(403);
        }
    }
}