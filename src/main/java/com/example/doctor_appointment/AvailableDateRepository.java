package com.example.doctor_appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    // Nirdishto date diye AvailableDate khuje ber korar jonno method
    AvailableDate findByAvailableDate(LocalDate availableDate);
}