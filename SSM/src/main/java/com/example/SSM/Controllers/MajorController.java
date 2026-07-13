package com.example.SSM.Controllers;

import com.example.SSM.Entity.Major;
import com.example.SSM.Service.MajorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/SSM/majors")
@Tag(name = "Major Management", description = "APIs for managing majors")

public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping
    public List<Major> getAll() {
        return majorService.getAllMajors();
    }

    @PostMapping
    public Major create(@Valid @RequestBody Major major) {
        return majorService.saveMajor(major);
    }

    @PutMapping("/{id}")
    public Major update(@PathVariable Long id,@Valid @RequestBody Major major) {
        return majorService.updateMajor(id, major);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        majorService.deleteMajor(id);
        return "This Major has been deleted.";
    }
}
