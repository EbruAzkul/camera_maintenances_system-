package com.kodhnk.base.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spare_parts")
@Entity
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partName;
    private String specification;
    private String instructions;

    @ManyToMany(mappedBy = "usedParts")
    private List<CameraInspection> inspections;
}
