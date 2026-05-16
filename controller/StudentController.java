package com.studentmanagementspringboot.controller;

import com.studentmanagementspringboot.dto.APIResponseDTO;
import com.studentmanagementspringboot.dto.StudentRequestDTO;
import com.studentmanagementspringboot.dto.StudentResponseDTO;
import com.studentmanagementspringboot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//- `GET /api/students` → kthen te gjithe studentet
//- `GET /api/students/{id}` → kthen nje student sipas id
//- `GET /api/students/level/{level}` → filter sipas nivelit
//- `GET /api/students/year/{year}` → filter sipas vitit
//- `POST /api/students` → shto nje student
//- `PUT /api/students/{id}` → update nje student
//- `DELETE /api/students/{id}` → fshi nje student

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO<List<StudentResponseDTO>>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(new APIResponseDTO<>(true, "Students fetched successfully", students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDTO<StudentResponseDTO>> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id)
                .map(s -> ResponseEntity.ok(new APIResponseDTO<>(true, "Student found", s)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new APIResponseDTO<>(false, "Student not found with id: " + id)));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<APIResponseDTO<List<StudentResponseDTO>>> getByLevel(@PathVariable String level) {
        List<StudentResponseDTO> students = studentService.getStudentsByLevel(level);
        return ResponseEntity.ok(new APIResponseDTO<>(true, "Students fetched by level", students));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<APIResponseDTO<List<StudentResponseDTO>>> getByYear(@PathVariable int year) {
        try {
            List<StudentResponseDTO> students = studentService.getStudentsByYear(year);
            return ResponseEntity.ok(new APIResponseDTO<>(true, "Students fetched by year", students));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponseDTO<>(false, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<APIResponseDTO<StudentResponseDTO>> addStudent(@Valid @RequestBody StudentRequestDTO request) {
        try {
            StudentResponseDTO created = studentService.addStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponseDTO<>(true, "Student added successfully", created));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new APIResponseDTO<>(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponseDTO<StudentResponseDTO>> updateStudent(@PathVariable int id,
                                                                            @Valid @RequestBody StudentRequestDTO request) {
        try {
            StudentResponseDTO updated = studentService.updateStudent(id, request);
            return ResponseEntity.ok(new APIResponseDTO<>(true, "Student updated successfully", updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponseDTO<>(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponseDTO<Void>> deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok(new APIResponseDTO<>(true, "Student deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponseDTO<>(false, e.getMessage()));
        }
    }
}