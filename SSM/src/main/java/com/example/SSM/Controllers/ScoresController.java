package com.example.SSM.Controllers;

import com.example.SSM.Entity.Scores;
import com.example.SSM.Service.ScoresService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/SSM/scores")
@Tag(name = "Score Management", description = "APIs for managing scores")

public class ScoresController {

    @Autowired
    private ScoresService scoresService;

    @GetMapping
    public List<Scores> getAll() {
        return scoresService.getAllScores();
    }

    @PostMapping
    public Scores create(@Valid @RequestBody Scores scores) { return scoresService.saveScores(scores);}

    @PutMapping("/{id}")
    public Scores update(@Valid @PathVariable Long id, @RequestBody Scores scores) {
        return scoresService.updateScores(id, scores);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        scoresService.deleteScores(id);
        return "This score has been deleted.";
    }
}
