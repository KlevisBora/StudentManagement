package com.studentmanagementspringboot.dto;

import jakarta.validation.constraints.*;

public class StudentRequestDTO {
//Cfare dergon klienti ne lidhje me studentin qe do te regjistroje(id eshte e gjeneruar automatikisht)
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Study level is required")
    private String studyLevel;

    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 5, message = "Year must be at most 5")
    private Integer year;  // Integer cause "Annotations are not allowed"

    public StudentRequestDTO() {}

    public StudentRequestDTO(String name, String email, String studyLevel, Integer year) {
        this.name = name;
        this.email = email;
        this.studyLevel = studyLevel;
        this.year = year;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStudyLevel() { return studyLevel; }
    public void setStudyLevel(String studyLevel) { this.studyLevel = studyLevel; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}