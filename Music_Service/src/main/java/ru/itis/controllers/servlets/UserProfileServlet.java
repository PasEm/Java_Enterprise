package ru.itis.controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.forms.UserForm;
import ru.itis.models.User;
import ru.itis.services.LoginService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/profile/ftl")
public class UserProfileServlet extends HttpServlet {
    private UserService userService;
    private LoginService loginService;
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        loginService = (LoginService) context.getAttribute("loginService");
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).get();

        String authorisationStatus = request.getParameter("dropdownProfile");
        request.setAttribute("creator", user);

        if (authorisationStatus != null) {
            String json;
            if (authorisationStatus.equals("load")) {
                json = objectMapper.writeValueAsString(user);
            } else {
                json = objectMapper.writeValueAsString("");
                loginService.signOut(user);
            }
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else request.getRequestDispatcher("/ftl/profile.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).get();

        String email = request.getParameter("email");
        String surname = request.getParameter("surname");
        String firstName = request.getParameter("first_name");
        String country = request.getParameter("country");
        String date = request.getParameter("birthdate");
        LocalDate birthdate = date.equals("") ? null : Date.valueOf(date).toLocalDate();
        String lastPassword = request.getParameter("last_password");
        String  newPassword = request.getParameter("new_password");

     //   boolean isCorrect = userService.isEqualPassword(lastPassword, creator.getHashPassword());

        UserForm userForm = UserForm.builder()
                .login(user.getLogin())
                .email(email.equals("") ? user.getEmail() : email)
         //      .password(isCorrect ? newPassword : creator.getHashPassword())
                .firstName(firstName.equals("") ? user.getFirstName() : firstName)
                .surname(surname.equals("") ? user.getSurname() : surname)
                .country(country.equals("") ? user.getCountry() : country)
                .birthdate(birthdate == null ? user.getBirthdate() : birthdate)
                .build();

      //  userService.updateUser(userForm, isCorrect);

        response.sendRedirect("/main/ftl");
    }
}
