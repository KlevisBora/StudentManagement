package com.studentmanagementspringboot.dto;

import com.studentmanagementspringboot.models.Student;

public class StudentResponseDTO {
//Cfare kthen serveri pas shtimit ose update te nje studenti
    private int id;
    private String name;
    private String email;
    private String studyLevel;
    private int year;

    public StudentResponseDTO() {}

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.studyLevel = student.getStudyLevel();
        this.year = student.getYear();
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