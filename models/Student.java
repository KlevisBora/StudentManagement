package com.studentmanagementspringboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "students")
@JsonPropertyOrder({"id", "name", "email", "studyLevel", "year"})
public class Student {
//Tabela e studenteve ne database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Study level is required")
    @Column(name = "study_level", nullable = false)
    private String studyLevel;

    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 5, message = "Year must be at most 5")
    @Column(nullable = false)
    private int year;

    public Student() {}

    public Student(String name, String email, String studyLevel, int year) {
        this.name = name;
        this.email = email;
        this.studyLevel = studyLevel;
        this.year = year;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStudyLevel() { return studyLevel; }
    public void setStudyLevel(String studyLevel) { this.studyLevel = studyLevel; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}