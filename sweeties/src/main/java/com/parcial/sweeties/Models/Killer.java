package com.parcial.sweeties.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Entity
@Table
public class Killer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column
    private String name;
    @Setter
    @Column
    private String last_name;
    @Setter
    @Column
    private String nickname;
    @Setter
    @Column
    private String description;

}
