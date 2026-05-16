package com.studentmanagementspringboot.repository;

import com.studentmanagementspringboot.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface StudentRepository extends JpaRepository<Student, Integer> {
        List<Student> findByStudyLevel(String studyLevel);
        List<Student> findByYear(int year);
        boolean existsByEmail(String email);
    }

