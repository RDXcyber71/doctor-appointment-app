package com.example.doctor_appointment;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime; // LocalTime import kora hoyeche

@Entity
@Table(name = "available_dates")
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available_date", unique = true) // Date gulo unique thaka uchit
    private LocalDate availableDate;

    // Notun field: Oi diner jonno appointment shurur shomoy
    @Column(name = "start_time")
    private LocalTime startTime;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }

    // Notun field-er jonno Getter and Setter
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
}