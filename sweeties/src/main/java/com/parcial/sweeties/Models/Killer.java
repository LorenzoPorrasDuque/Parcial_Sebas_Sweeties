package com.parcial.sweeties.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
public class Killer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column
    private String name;
    @Setter
    @Getter
    @Column
    private String last_name;
    @Setter
    @Getter
    @Column
    private String nickname;
    @Setter
    @Getter
    @Column
    private String description;

}
