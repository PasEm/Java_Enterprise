package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.transfer.CommentDto;
import ru.itis.transfer.EventDto;
import ru.itis.transfer.ExtendedEventDto;
import ru.itis.transfer.UserDto;
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
    public String getEvent (HttpServletRequest request,
                            @CookieValue(name = "auth", required = false) String auth,
                            @PathVariable(name = "id") Long id) {
        Optional<User> user = userService.getCurrentUserByCookieValue(auth);
        user.ifPresent(user1 -> request.setAttribute("user", user1));

        Optional<ExtendedEventDto> event = eventService.getEventById(id);

        if (event.isPresent()) {
            request.setAttribute("event", event.get());
            return "/event";
        } else return "redirect:/404";
    }

    @PostMapping("event/{id}/comment")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> addComments(@CookieValue(name = "auth") String auth,
                                                        @PathVariable(name = "id") Long eventId,
                                                        @RequestParam("comment") String comment) {
        Optional<User> user = userService.getCurrentUserByCookieValue(auth);

        List<CommentDto> comments = eventService.addComment(eventId, comment, user.get());

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/events")
    public String getEventPage(HttpServletRequest request,
                               @CookieValue(name = "auth", required = false) String auth) {
        User user = userService.getCurrentUserByCookieValue(auth).orElse(null);
        request.setAttribute("user", user);
        return "/events";
    }

    @GetMapping("/events/search")
    @ResponseBody
    public ResponseEntity<List<UserDto>> searchEvents (HttpServletResponse response,
                                                       @RequestParam(value = "participants") String searchTitle,
                                                       @CookieValue(name = "participantBasket", required = false) String basketId) {
        if (basketId == null) {
            basketId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("participantBasket", basketId);
            response.addCookie(cookie);
        }

        List<UserDto> result = eventService.searchParticipants(searchTitle);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/events/delete")
    @ResponseBody
    public ResponseEntity<List<UserDto>> deleteParticipant(@CookieValue(value = "participantBasket") String basketId,
                                                           @RequestParam(value = "deleteParticipant") String participant) {
        return ResponseEntity.ok(eventService.deleteParticipant(participant, basketId));
    }

    @PostMapping("/events/add")
    @ResponseBody
    public ResponseEntity<List<UserDto>> addParticipant(@CookieValue(value = "participantBasket") String basketId,
                                                        @RequestParam(value = "currentParticipant") String participant) {
        return ResponseEntity.ok(eventService.addParticipant(participant, basketId));
    }

    @PostMapping("/events/clear")
    @ResponseBody
    public ResponseEntity<Object> clearBasket(@CookieValue(value = "participantBasket") String basketId) {
        eventService.clearParticipantBasket(basketId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    @ResponseBody
    public ResponseEntity<List<EventDto>> getEventsByBasket(@CookieValue(value = "participantBasket") String basketId) {
        List<UserDto> participants = eventService.getParticipantBasket(basketId);
        return ResponseEntity.ok(eventService.getEventsByParticipants(participants));
    }
}