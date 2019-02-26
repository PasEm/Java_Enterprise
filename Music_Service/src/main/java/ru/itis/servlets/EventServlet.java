package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.EventService;
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
import java.util.List;
import java.util.UUID;

@WebServlet("/events/ftl")
public class EventServlet extends HttpServlet {
    private EventService eventService;
    private UserService userService;
    private LoginService loginService;
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        eventService = (EventService) context.getAttribute("eventService");
        userService = (UserService) context.getAttribute("userService");
        loginService = (LoginService) context.getAttribute("loginService");

        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).orElse(null);

        String searchTitle = request.getParameter("participants");
        String authorisationStatus = request.getParameter("dropdownProfile");
        String json = null;

        if (searchTitle != null) {
            List<UserDto> result = eventService.searchParticipants(searchTitle);
            json = searchTitle.equals("") ? objectMapper.writeValueAsString(null) : objectMapper.writeValueAsString(result);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        if (authorisationStatus != null) {
            if (authorisationStatus.equals("load")) {
                json = objectMapper.writeValueAsString(user);
            } else {
                json = objectMapper.writeValueAsString("");
                loginService.signOut(user);
            }
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
        if (json == null) {
            request.getRequestDispatcher("/ftl/events.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<UserDto> participants;
        String basketId = eventService.getBasketId(request.getCookies());

        if (basketId == null) {
            basketId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("participantBasket", basketId);
            response.addCookie(cookie);
        }

        String addUser = request.getParameter("currentParticipant");
        String deleteUser = request.getParameter("deleteParticipant");
        String seeEvent = request.getParameter("events");
        String clearBasket = request.getParameter("clearing");

        String json;

        if (addUser != null) {
            eventService.addParticipant(addUser, basketId);
        }
        if (deleteUser != null) {
            eventService.deleteParticipant(deleteUser, basketId);
        }
        if (clearBasket != null) {
            eventService.clearParticipantBasket(basketId);
        }
        participants = eventService.getParticipantBasket(basketId);
        json = objectMapper.writeValueAsString(participants);

        if (seeEvent != null) {
            json = objectMapper.writeValueAsString(eventService.getEventsByParticipants(participants));
        }

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}