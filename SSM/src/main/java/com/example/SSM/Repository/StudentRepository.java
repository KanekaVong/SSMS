package com.example.SSM.Repository;

import com.example.SSM.dto.StudentScoreDTO;
import com.example.SSM.dto.StudentGradeSummaryDTO;
import com.example.SSM.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

    @Query(value = "SELECT st.StudentID, st.StudentName, su.SubjectName AS Subject, sc.score AS Score " +
            "FROM Student st " +
            "JOIN Scores sc ON st.StudentID = sc.studentid " +
            "JOIN Subject su ON sc.subjectid = su.SubjectID " +
            "WHERE st.StudentID = :studentId", nativeQuery = true)
    List<StudentScoreDTO> findStudentScores(@Param("studentId") Long studentId);

    @Query(value = "SELECT st.StudentID, st.StudentName, COUNT(sc.score) AS TotalSubjects, " +
            "SUM(sc.score) AS TotalScores, ROUND(AVG(sc.score), 2) AS Average, " +
            "ROUND(AVG((sc.score) / 100) * 4, 2) AS GPA " +
            "FROM Student st " +
            "JOIN Scores sc ON st.StudentID = sc.studentid " +
            "GROUP BY st.StudentID, st.StudentName", nativeQuery = true)
    List<StudentGradeSummaryDTO> getGradeSummaries();
}
