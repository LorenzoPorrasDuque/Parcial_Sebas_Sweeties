package com.parcial.sweeties.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
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

    // Relaciones

        // Relacion Family - Victim (1 - n)
        @OneToMany(mappedBy = "family")

        private List<Victim> victim;


}
