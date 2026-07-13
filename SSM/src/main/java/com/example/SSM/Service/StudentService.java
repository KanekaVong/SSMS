package com.example.SSM.Service;

import com.example.SSM.Entity.Major;
import com.example.SSM.Entity.Student;
import com.example.SSM.Repository.MajorRepository;
import com.example.SSM.Repository.StudentRepository;
import com.example.SSM.ResourceNotFoundException;
import com.example.SSM.dto.StudentGradeSummaryDTO;
import com.example.SSM.dto.StudentScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MajorRepository majorRepository;

    public List<StudentScoreDTO> getStudentScores(Long id) {
        return studentRepository.findStudentScores(id);
    }

    public List<StudentGradeSummaryDTO> getAllGradeSummaries() {
        return studentRepository.getGradeSummaries();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        if (student.getMajorID() == null ) {
            throw new IllegalArgumentException("A valid Major ID must be provided");
        }

        Long targetedMajorId = student.getMajorID();
        if (!majorRepository.existsById(targetedMajorId)) {
            throw new ResourceNotFoundException("Major table does not contain a record with ID: " + targetedMajorId);
        }

        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(existingStudent -> {
            if (updatedStudent.getMajorID() != null) {
                Long majorID = updatedStudent.getMajorID();
                Major existingMajor = majorRepository.findById(majorID)
                        .orElseThrow(() -> new ResourceNotFoundException("Major not found with ID: " + majorID));
                existingStudent.setMajor(existingMajor);
            }
            existingStudent.setStudentName(updatedStudent.getStudentName());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setDob(updatedStudent.getDob());
            existingStudent.setPob(updatedStudent.getPob());
            existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new ResourceNotFoundException("Student is not found with id " + id));
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Student is not found with id " + id);
        }
    }


}
