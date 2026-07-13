package com.example.SSM.Repository;

import com.example.SSM.Entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository <Major, Long>   {

}
