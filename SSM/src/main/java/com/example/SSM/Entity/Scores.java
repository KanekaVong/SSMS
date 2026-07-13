package com.example.SSM.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Scores")
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCOREID")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long scoreID;

    @Column(name = "STUDENTID")
    @Schema(example = "", accessMode = Schema.AccessMode.READ_WRITE)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "STUDENTID", insertable = false, updatable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Student student;

    @Column(name = "SUBJECTID")
    @Schema(example = "", accessMode = Schema.AccessMode.READ_WRITE)
    private Long subjectId;

    @ManyToOne
    @JoinColumn(name = "SUBJECTID", insertable = false, updatable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Subject subject;

    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be greater than 100")
    @Column(name = "SCORE")
    private Long score;

}
