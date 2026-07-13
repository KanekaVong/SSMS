package com.example.SSM.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENTID")
    @Schema(description = "The unique auto-generated database identifier for the student",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long studentId;

    @NotBlank(message = "Student Name cannot be empty")
    @Size(min = 2, message = "Student Name must be at least 2 characters long")
    @Column(name = "STUDENTNAME", length = 70)
    private String studentName;

    @NotBlank(message = "Gender cannot be empty")
    @Column(name = "GENDER", length = 15)
    private String gender;

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


    @NotNull(message = "Date of birth cannot be empty")
    @Past(message = "Date of birth must be a date in the past")
    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @NotBlank(message = "Place of Birth cannot be empty")
    @Column(name = "POB", length = 70)
    private String pob;

    @NotBlank(message = "Phone Number cannot be empty")
    @Size(min = 2, message = "Phone Number must be at least 8 characters long")
    @Column(name = "PHONENUMBER")
    private String phoneNumber;

}
