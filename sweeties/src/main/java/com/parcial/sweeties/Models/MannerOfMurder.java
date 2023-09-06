package com.parcial.sweeties.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // Relaciones

        // Relacion MannerOfMurder - Victim (1 - 1)
        @OneToOne
        @JoinColumn(name = "victim_id")
        @JsonIgnore
        private Victim victim;

        // Relacion MannerOfMurder - Victim (n - n)
        @ManyToMany
        @JoinTable(
                name = "Killer_MannerOfMurder", joinColumns = @JoinColumn(name = "mannerOfMurder_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "killer_id", referencedColumnName = "id")
        )
        @JsonIgnore
        private List<Killer> killer;
}