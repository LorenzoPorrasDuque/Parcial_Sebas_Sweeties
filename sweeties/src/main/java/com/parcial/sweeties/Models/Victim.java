package com.parcial.sweeties.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
@Getter
public class Victim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Setter
    private String name;
    @Column
    @Setter
    private String last_name;
    @Column
    @Setter
    private String age;
    @Column
    @Setter
    private String occupation;


}
