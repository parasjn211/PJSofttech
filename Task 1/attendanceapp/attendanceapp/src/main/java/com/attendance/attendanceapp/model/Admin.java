package com.attendance.attendanceapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="admin")

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Username cannot be empty")
    @NotBlank(message="Username cannot be blank")
    private String username;
    @NotEmpty(message="Password cannot be empty")
    @NotBlank(message="Password cannot be blank")
    private String password;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

}
