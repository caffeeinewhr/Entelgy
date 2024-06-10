package com.entelgy.project.demo.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entelgy.project.demo.Entity.Event;
import com.entelgy.project.demo.Entity.User;
import com.entelgy.project.demo.Repository.EventRepository;
import com.entelgy.project.demo.Repository.UserRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    
    public Event saveEvent(Event event) {
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }

    public Event saveEventForUser(Event event, User user) {
        event.setUser(user);
        Event savedEvent = eventRepository.save(event);
        return savedEvent;
    }

    public Event assignEventToUser(Long eventId, Long userId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (eventOpt.isPresent() && userOpt.isPresent()) {
            Event event = eventOpt.get();
            if (event.getUser() != null) {
                return null;
            }
            User user = userOpt.get();
            event.setUser(user);
            return eventRepository.save(event);
        }
        return null;
    }

    public Event updateEvent(Long id, Event event) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            Event updatedEvent = existingEvent.get();
            updatedEvent.setTitle(event.getTitle());
            updatedEvent.setMessage(event.getMessage());
            updatedEvent.setStartDate(event.getStartDate());
            updatedEvent.setEndDate(event.getEndDate());
            updatedEvent.setStart(event.getStart());
            updatedEvent.setEnd(event.getEnd());
            return eventRepository.save(updatedEvent);
        }
        return null;
    }
    
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByUser(User user) {
        return eventRepository.findByUser(user);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
}
