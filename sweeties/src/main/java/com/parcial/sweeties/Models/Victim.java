package com.parcial.sweeties.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
public class Victim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Setter
    @Getter
    private String name;
    @Column
    @Setter
    @Getter
    private String last_name;
    @Column
    @Setter
    @Getter
    private String age;
    @Column
    @Setter
    @Getter
    private String occupation;
    @Column
    @Setter
    @Getter
    private String description;

}
