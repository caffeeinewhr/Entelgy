package com.entelgy.project.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.entelgy.project.demo.Entity.Event;
import com.entelgy.project.demo.Entity.User;
import com.entelgy.project.demo.Service.EventService;
import com.entelgy.project.demo.Service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/user/{userId}")
    public List<Event> getEventsByUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(eventService::getEventsByUser).orElse(null);
    }

    @GetMapping("/{id}")
    public Optional<Event> getEvent(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}