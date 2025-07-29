package com.example.doctor_appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class AppointmentController {

    @Autowired
    private AvailableDateRepository availableDateRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private final int APPOINTMENT_DURATION_MINUTES = 20;

    @GetMapping("/")
    public String showMainForm(Model model) {
        List<AvailableDate> dates = availableDateRepository.findAll();
        List<String> availableDatesAsString = dates.stream()
                .map(d -> d.getAvailableDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .collect(Collectors.toList());
        model.addAttribute("availableDates", availableDatesAsString);
        return "index";
    }

    @PostMapping("/api/submit_appointment")
    @ResponseBody
    public Appointment handleSubmitAppointment(@RequestBody Map<String, String> payload) {
        LocalDate appointmentDate = LocalDate.parse(payload.get("appointmentDate"));

        // 1. Database theke selected date-er jonno start time khuje ber kora
        AvailableDate availableDate = availableDateRepository.findByAvailableDate(appointmentDate);
        if (availableDate == null) {
            // Jodi kono karone date-ti database-e na thake
            throw new RuntimeException("Selected date is not available.");
        }
        LocalTime appointmentStartTime = availableDate.getStartTime();

        // 2. Appointment object toiri kora
        Appointment appointment = new Appointment();
        appointment.setPatientName(payload.get("patientName"));
        appointment.setGender(payload.get("gender"));
        appointment.setAge(Integer.parseInt(payload.get("age")));
        appointment.setWeight(payload.get("weight").isEmpty() ? 0.0 : Double.parseDouble(payload.get("weight")));
        appointment.setHeight(payload.get("height").isEmpty() ? 0.0 : Double.parseDouble(payload.get("height")));
        appointment.setMobileNumber(payload.get("mobileNumber"));
        appointment.setEmail(payload.get("email"));
        appointment.setAddress(payload.get("address"));
        appointment.setAppointmentDate(appointmentDate);
        appointment.setSubmitTime(LocalDateTime.now());
        appointment.setVisitFee(0.00);

        // 3. Serial number hisheb kora
        int serial = appointmentRepository.countByAppointmentDate(appointmentDate) + 1;
        appointment.setSerialNo(serial);

        // 4. Database theke paoa start time diye appointment time hisheb kora
        LocalTime appointmentTime = appointmentStartTime.plusMinutes((long) (serial - 1) * APPOINTMENT_DURATION_MINUTES);
        appointment.setAppointmentTime(appointmentTime);
        
        appointment.setTokenId(generateToken());

        Appointment savedAppointment = appointmentRepository.save(appointment);
        
        return savedAppointment;
    }
    
    private String generateToken() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();
        Random rnd = new Random();
        while (token.length() < 4) {
            int index = (int) (rnd.nextFloat() * characters.length());
            token.append(characters.charAt(index));
        }
        return token.toString();
    }
}