package com.hms.appointment.dto;

import com.hms.appointment.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate appointmentTime;
    private Status status;
    private String reason;
    private String notes;

    public Appointment toEntity(){
        return new Appointment(id, patientId, doctorId, appointmentTime, status, reason, notes);
    }
}
