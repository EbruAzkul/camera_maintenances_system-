package com.kodhnk.base.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "camerazones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CameraZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "cameranetwork_id")
    private CameraNetwork cameranetwork;
}
