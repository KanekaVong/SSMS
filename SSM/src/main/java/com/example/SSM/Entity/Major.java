package com.example.SSM.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAJORID")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long majorID;

    @NotBlank(message = "Major Name cannot be empty")
    @Size(min = 2, message = "Major Name must be at least 2 characters long")
    @Column(name = "MAJORNAME", length = 70)
    private String majorName;


}
