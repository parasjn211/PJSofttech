package com.attendance.attendanceapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminRegistrationDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
