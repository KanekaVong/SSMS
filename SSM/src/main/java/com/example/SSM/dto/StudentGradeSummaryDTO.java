package com.example.SSM.dto;

public interface StudentGradeSummaryDTO {
    Long getStudentID();
    String getStudentName();
    Long getTotalSubjects();
    Integer getTotalScores();
    Double getAverage();
    Double getGpa();
}
