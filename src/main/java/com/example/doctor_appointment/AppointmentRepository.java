package com.example.doctor_appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository // 1. Spring-কে বোঝানোর জন্য যে এটি একটি ডেটাবেস রিপোজিটরি
public interface AppointmentRepository extends JpaRepository<Appointment, Long> { // 2. JpaRepository-কে এক্সটেন্ড করা

    // 3. সিরিয়াল নম্বর গণনার জন্য একটি কাস্টম মেথড
    // Spring Data JPA মেথডের নাম দেখেই নিজে থেকে কোয়েরি তৈরি করে নেবে
    int countByAppointmentDate(LocalDate appointmentDate);

}