package com.apicadeia.apicadeia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prisioneiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prisioneiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private int tempoDePena;

    @ManyToOne
    @JoinColumn(name = "numero", nullable = false)
    private Cela cela;
}
