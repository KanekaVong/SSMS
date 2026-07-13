package com.example.SSM.Controllers;

import com.example.SSM.Entity.Student;
import com.example.SSM.Service.StudentService;
import com.example.SSM.dto.StudentGradeSummaryDTO;
import com.example.SSM.dto.StudentScoreDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/SSM/students")
@Tag(name = "Student's Management", description = "APIs for managing student records")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student create(@Valid @RequestBody Student student) { return studentService.saveStudent(student);}

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,@Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "student with the id " + id + " has been deleted";
    }

    @GetMapping("/{id}/scores")
    public ResponseEntity<List<StudentScoreDTO>> getStudentScores(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentScores(id));
    }

    @GetMapping("/grade-summaries")
    public ResponseEntity<List<StudentGradeSummaryDTO>> getGradeSummaries() {
        return ResponseEntity.ok(studentService.getAllGradeSummaries());
    }
}
