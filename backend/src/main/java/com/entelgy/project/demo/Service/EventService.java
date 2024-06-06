package com.entelgy.project.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entelgy.project.demo.Entity.Event;
import com.entelgy.project.demo.Entity.User;
import com.entelgy.project.demo.Repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventsByUser(User user) {
        return eventRepository.findByUser(user);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
}
