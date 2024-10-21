package com.apicadeia.apicadeia.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cela {
    @Setter
    private int numero;


    private List<Prisioneiro> prisioneiros;

    public Cela(int numero) {
        this.numero = numero;
        this.prisioneiros = new ArrayList<>();
    }


    public void adicionarPrisioneiro(Prisioneiro prisioneiro) {
        prisioneiros.add(prisioneiro);
    }

    public void removerPrisioneiro(Prisioneiro prisioneiro) {
        prisioneiros.remove(prisioneiro);
    }
}
