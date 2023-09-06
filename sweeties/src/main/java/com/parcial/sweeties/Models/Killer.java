package com.parcial.sweeties.Models;

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

    // Relaciones

        // Relacione Killer - Victim (1 - n)
        @OneToMany(mappedBy = "killer")
        private List<Victim> victim;

        // Relacion Killer - MannerOfMurder (n - n)
        @ManyToMany(mappedBy = "killer")
        private List<MannerOfMurder> mannerOfMurder;

}
