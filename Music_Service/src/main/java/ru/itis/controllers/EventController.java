package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.transfer.EventDto;
import ru.itis.transfer.UserDto;
import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.services.EventService;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class EventController {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEvent (HttpServletRequest request,
                                              @PathVariable(name = "id") Long id) {
        Optional<User> user = userService.getCurrentUserByCookieValue(request.getCookies());
        user.ifPresent(user1 -> request.setAttribute("user", user1));

        Optional<EventDto> event = eventService.getEventById(id);

        if (event.isPresent()) {
            request.setAttribute("event", event.get());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.status(404).build();
    }

    @GetMapping("/events")
    public String getEventPage(HttpServletRequest request) {
        User user = userService.getCurrentUserByCookieValue(request.getCookies()).orElse(null);
        request.setAttribute("user", user);
        return "/events";
    }

    @GetMapping("/events/search")
    @ResponseBody
    public ResponseEntity<List<UserDto>> searchEvents (HttpServletResponse response,
                                                       @RequestParam(value = "participants") String searchTitle,
                                                       @RequestParam(value = "participantBasket", required = false) String basketId) {
        if (basketId == null) {
            basketId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("participantBasket", basketId);
            response.addCookie(cookie);
        }

        List<UserDto> result = eventService.searchParticipants(searchTitle);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/events/delete")
    public void deleteParticipant(@RequestParam(value = "participantBasket") String basketId,
                                  @RequestParam(value = "deleteParticipant") String participant) {
        eventService.deleteParticipant(participant, basketId);
    }

    @PostMapping("/events/add")
    public void addParticipant(@RequestParam(value = "participantBasket") String basketId,
                                  @RequestParam(value = "currentParticipant") String participant) {
        eventService.addParticipant(participant, basketId);
    }

    @PostMapping("/events/clear")
    public void clearBasket(@RequestParam(value = "participantBasket") String basketId,
                            @RequestParam(value = "clearing") String trigger) {
        eventService.clearParticipantBasket(basketId);
    }

    @PostMapping("/events")
    @ResponseBody
    public ResponseEntity<List<Event>> getEventsByBasket(@RequestParam(value = "participantBasket") String basketId,
                                                         @RequestParam(value = "events") String trigger) {
        List<UserDto> participants = eventService.getParticipantBasket(basketId);
        return ResponseEntity.ok(eventService.getEventsByParticipants(participants));
    }
}