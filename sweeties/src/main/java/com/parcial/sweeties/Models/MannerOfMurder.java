package com.parcial.sweeties.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table
public class MannerOfMurder implements Serializable {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column
    private String type;
    @Setter
    @Getter
    @Column
    private String description;
}