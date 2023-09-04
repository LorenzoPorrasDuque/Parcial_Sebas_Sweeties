package com.parcial.sweeties.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
public class Family implements Serializable {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Setter
    @Getter
    private String name;
    @Column
    @Setter
    @Getter
    private int number_members;


}
