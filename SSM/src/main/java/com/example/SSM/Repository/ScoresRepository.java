package com.example.SSM.Repository;

import com.example.SSM.Entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoresRepository extends JpaRepository <Scores, Long> {


}
