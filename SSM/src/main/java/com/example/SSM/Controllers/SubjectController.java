package com.example.SSM.Controllers;

import com.example.SSM.Entity.Subject;
import com.example.SSM.Service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/SSM/subjects")
@Tag(name = "Subject Management", description = "APIs for managing subjects")

public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAll() {
        return subjectService.getAllSubjects();

    }

    @PostMapping
    public Subject create(@Valid @RequestBody Subject subject) { return subjectService.saveSubject(subject);}

    @PutMapping("/{id}")
    public Subject update(@PathVariable Long id,@Valid @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "This Subject has been deleted.";
    }
}
