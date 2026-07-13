package com.example.SSM.Service;

import com.example.SSM.Entity.Major;
import com.example.SSM.Entity.Subject;
import com.example.SSM.Repository.MajorRepository;
import com.example.SSM.Repository.SubjectRepository;
import com.example.SSM.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MajorRepository majorRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject saveSubject(Subject subject) {
        if (subject.getMajorID() == null) {
            throw new IllegalArgumentException("A valid Major ID must be provided");
        }

        Long targetedMajorId = subject.getMajorID();
        if (!majorRepository.existsById(targetedMajorId)) {
            throw new ResourceNotFoundException("Major table does not contain a record with ID: " + targetedMajorId);
        }

        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject updatedSubject) {
        return subjectRepository.findById(id).map(existingSubject -> {
            existingSubject.setSubjectName(updatedSubject.getSubjectName());
            existingSubject.setMajorID(updatedSubject.getMajorID());
            return subjectRepository.save(existingSubject);
        }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + id));
    }

    public void deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Subject Not Found with id " + id);
        }
    }
}
