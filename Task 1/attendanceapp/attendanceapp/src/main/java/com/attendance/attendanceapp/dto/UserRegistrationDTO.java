package com.attendance.attendanceapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class UserRegistrationDTO {

    @NotEmpty(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits long")
    private String phone;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String username, String email, String password, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public @NotEmpty(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username is required") String username) {
        this.username = username;
    }

    public @Email(message = "Invalid email format") @NotEmpty(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email format") @NotEmpty(message = "Email is required") String email) {
        this.email = email;
    }

    public @Size(min = 6, message = "Password must be at least 6 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Password must be at least 6 characters long") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits long") String getPhone() {
        return phone;
    }

    public void setPhone(@NotEmpty(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits long") String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationDTO that = (UserRegistrationDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, phone);
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
