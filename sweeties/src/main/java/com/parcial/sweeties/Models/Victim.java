package com.parcial.sweeties.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    // Relaciones

        // Relacion Victim - MannerOfMurder (1 - 1)
        @OneToOne(mappedBy = "victim")
        @Getter
        @Setter
        private MannerOfMurder mannerOfMurder;

        // Relacion Victim - Family (n - 1)
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "family_id")
        @Getter
        @Setter
        private Family family;

    // Relacion Victim - Killer
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="killer_id")
        @Getter
        @Setter
        private Killer killer;

}
