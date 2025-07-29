package com.example.doctor_appointment;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_name")
    private String patientName;

    private String gender;

    private int age;

    private Double weight;

    private Double height;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String email;

    private String address;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    // Notun field: Appointment Time
    @Column(name = "appointment_time")
    private LocalTime appointmentTime;

    @Column(name = "token_id", unique = true)
    private String tokenId;

    @Column(name = "serial_no")
    private int serialNo;

    @Column(name = "visit_fee")
    private Double visitFee;

    @Column(name = "submit_time", updatable = false)
    private LocalDateTime submitTime;

    // Notun field: Attendance
    private String attendance = "Pending"; // Default value

    // Notun field: Payment Status
    @Column(name = "payment_status")
    private String paymentStatus = "Not Paid"; // Default value

    public Appointment() {
    }

    // --- Getters and Setters for all fields ---
    // (Existing getters and setters)

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getTokenId() { return tokenId; }
    public void setTokenId(String tokenId) { this.tokenId = tokenId; }
    public int getSerialNo() { return serialNo; }
    public void setSerialNo(int serialNo) { this.serialNo = serialNo; }
    public Double getVisitFee() { return visitFee; }
    public void setVisitFee(Double visitFee) { this.visitFee = visitFee; }
    public LocalDateTime getSubmitTime() { return submitTime; }
    public void setSubmitTime(LocalDateTime submitTime) { this.submitTime = submitTime; }
}