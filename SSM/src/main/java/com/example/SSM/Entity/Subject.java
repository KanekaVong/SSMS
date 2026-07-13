package com.example.SSM.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECTID")
    @Schema(description = "The unique auto-generated database identifier for the student",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long subjectID;

    @NotBlank(message = "Subject Name cannot be empty")
    @Size(min = 2, message = "Subject Name must be at least 2 characters long")
    @Column(name = "SUBJECTNAME", length = 70)
    private String subjectName;

    @Column(name = "MAJORID")
    @Schema(
            description = "The numeric ID of the existing Major to link this subject to.",
            example = "",
            accessMode = Schema.AccessMode.READ_WRITE
    )
    private Long majorID;

    @ManyToOne
    @JoinColumn(name = "MAJORID", insertable = false, updatable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Major major;


}
