package com.kodhnk.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String model;
    private LocalDate installationDate;
    private String status;

    @ManyToMany
    @JoinTable(
            name = "camera_networks_cameras",
            joinColumns = @JoinColumn(name = "camera_id"),
            inverseJoinColumns = @JoinColumn(name = "network_id")
    )
    @JsonIgnore
    private Set<CameraNetwork> networks;

    @OneToMany(mappedBy = "camera", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CameraInspection> inspections;

    @OneToMany(mappedBy = "camera", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CameraMaintenance> maintenances;
}