package com.example.SSM.Service;

import com.example.SSM.Entity.Major;
import com.example.SSM.Repository.MajorRepository;
import com.example.SSM.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorRepository majorRepository;

    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    public Major saveMajor(Major major) {
        return majorRepository.save(major);
    }

    public Major updateMajor(Long id, Major updatedMajor) {
        return majorRepository.findById(id).map(existingMajor -> {
            existingMajor.setMajorName(updatedMajor.getMajorName());
            return majorRepository.save(existingMajor);
        }).orElseThrow(() ->  new ResourceNotFoundException("Major not found with id: " + id));
    }

    public void deleteMajor(Long id) {
        if (majorRepository.existsById(id)) {
            majorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Major not found with id: " + id);
        }
    }

}
