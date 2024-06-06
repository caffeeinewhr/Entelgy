package com.entelgy.project.demo.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalTime start;
    private LocalTime end;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
    
    public Event() {
    }

    public Event(String title, String message, LocalDateTime startDate, LocalDateTime endDate, LocalTime start,
            LocalTime end, User user) {
        this.title = title;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.start = start;
        this.end = end;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", message=" + message + ", startDate=" + startDate
                + ", endDate=" + endDate + ", start=" + start + ", end=" + end + "]";
    }

}
