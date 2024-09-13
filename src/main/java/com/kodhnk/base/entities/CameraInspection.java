package com.kodhnk.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "camera_inspections")
public class CameraInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    @JsonIgnore
    private Camera camera;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "technician_id")
    private Technician technician;

    private Date inspectionDate;
    private String issuesFound;
    private String actionsTaken;
    private String notes;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "inspection_parts",
            joinColumns = @JoinColumn(name = "inspection_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private List<SparePart> usedParts;
}