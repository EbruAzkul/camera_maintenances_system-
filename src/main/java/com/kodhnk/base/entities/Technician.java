package com.kodhnk.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technician")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specialty;

    @ManyToOne
    @JoinColumn(name = "cameraNetwork_id")
    @JsonIgnore
    private CameraNetwork cameraNetwork;

    @ManyToOne
    @JoinColumn(name = "cameraZone_id")
    @JsonIgnore
    private CameraZone cameraZone;

    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    private List<CameraMaintenance> cameraMaintenances;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
