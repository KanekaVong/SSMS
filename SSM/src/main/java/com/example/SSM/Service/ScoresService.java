package com.example.SSM.Service;


import com.example.SSM.Entity.Scores;
import com.example.SSM.Entity.Student;
import com.example.SSM.Entity.Subject;
import com.example.SSM.Repository.ScoresRepository;
import com.example.SSM.Repository.StudentRepository;
import com.example.SSM.Repository.SubjectRepository;
import com.example.SSM.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoresService {

    @Autowired
    private ScoresRepository scoresRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Scores> getAllScores() {return scoresRepository.findAll(); }

    public Scores saveScores(Scores scores) {
        if (scores.getStudentId() == null ) {
            throw new IllegalArgumentException("A valid Student ID must be provided");
        }

        Long targetedstudentID = scores.getStudentId();
        if (!studentRepository.existsById(targetedstudentID)) {
            throw new ResourceNotFoundException("Student table does not contain a record with ID: " + targetedstudentID);
        }

        if (scores.getSubjectId() == null) {
            throw new IllegalArgumentException("A valid Subject ID must be provided");
        }

        Long targetedsubjectID = scores.getSubjectId();
        if (!subjectRepository.existsById(targetedsubjectID)) {
            throw new ResourceNotFoundException("Subject table does not contain a record with ID: " + targetedsubjectID);
        }
        return scoresRepository.save(scores);
    }

    @Transactional
    public Scores updateScores(Long id, Scores updatedScores) {
        return scoresRepository.findById(id).map(existingScores -> {

            if (updatedScores.getStudent() != null) {
                Long studentId = updatedScores.getStudentId();
                Student existingStudent = studentRepository.findById(studentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
                existingScores.setStudent(existingStudent);
            }

            if (updatedScores.getSubject() != null) {
                Long subjectId = updatedScores.getSubjectId();
                Subject existingSubject = subjectRepository.findById(subjectId)
                        .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + subjectId));
                existingScores.setSubject(existingSubject);
            }


            existingScores.setScore(updatedScores.getScore());
            return scoresRepository.save(existingScores);
        }).orElseThrow(() -> new ResourceNotFoundException("Scores is not found with id " + id));
    }

    public void deleteScores(Long id) {
        if(scoresRepository.existsById(id)) {
            scoresRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Score Not Found With id " + id);
        }
    }

}
