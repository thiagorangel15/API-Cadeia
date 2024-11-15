package com.apicadeia.apicadeia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cela")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    @OneToMany(mappedBy = "cela", orphanRemoval = true)
    private List<Prisioneiro> prisioneiros = new ArrayList<>();
}
