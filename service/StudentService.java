package com.studentmanagementspringboot.service;

import com.studentmanagementspringboot.dto.StudentRequestDTO;
import com.studentmanagementspringboot.dto.StudentResponseDTO;
import com.studentmanagementspringboot.exceptions.DuplicateEmailException;
import com.studentmanagementspringboot.exceptions.ResourceNotFoundException;
import com.studentmanagementspringboot.exceptions.DuplicateEmailException;
import com.studentmanagementspringboot.models.Student;
import com.studentmanagementspringboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Merr gjithe studentet dhe i konverton ne StudentResponseDTO
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Gjen studentin by id
    public Optional<StudentResponseDTO> getStudentById(int id) {
        return studentRepository.findById(id)
                .map(StudentResponseDTO::new);
    }

    // Shikon nqs emaili ekziston njehere, pastaj e shton
    public StudentResponseDTO addStudent(StudentRequestDTO request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("A student with this email already exists.");
        }

        Student student = new Student(
                request.getName(),
                request.getEmail(),
                request.getStudyLevel(),
                request.getYear()
        );

        return new StudentResponseDTO(studentRepository.save(student));
    }

    // Gjen studentin ekzistent, ben update dhe shpeton editimin e fushes
    public StudentResponseDTO updateStudent(int id, StudentRequestDTO request) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setStudyLevel(request.getStudyLevel());
        existing.setYear(request.getYear());

        return new StudentResponseDTO(studentRepository.save(existing));
    }

    // Qe nga emri, thjesht gjen dhe fshin
    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    // Filter sipas Bachelor ose Master
    public List<StudentResponseDTO> getStudentsByLevel(String level) {
        List<StudentResponseDTO> students = studentRepository.findByStudyLevel(level)
                .stream()
                .map(StudentResponseDTO::new)
                .collect(Collectors.toList());

        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students found for level: " + level);
        }

        return students;
    }

    // Filter sipas vitit
    public List<StudentResponseDTO> getStudentsByYear(int year) {
        List<StudentResponseDTO> students = studentRepository.findByYear(year)
                .stream()
                .map(StudentResponseDTO::new)
                .collect(Collectors.toList());

        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students found for year: " + year);
        }

        return students;
    }
}