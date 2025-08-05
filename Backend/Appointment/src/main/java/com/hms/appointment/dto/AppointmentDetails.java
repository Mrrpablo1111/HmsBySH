package com.hms.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetails {
    private Long id;
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientPhone;
    private Long doctorId;
    private String doctorName;
    private String doctorEmail;
    private String doctorPhone;
    private LocalDate appointmentTime;
    private Status status;
    private String reason;
    private String notes;
}
