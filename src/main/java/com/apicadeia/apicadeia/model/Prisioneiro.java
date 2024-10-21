package com.apicadeia.apicadeia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.*;

@Getter
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prisioneiro {
    @Setter
    private int id;
    @Setter
    private String nome;
    @Setter
    private int idade;
    @Setter
    private int tempoDePena;


    @Setter
    private int numeroCela;



    public Prisioneiro(String nome, int idade, int tempoDePena, int numeroCela) {
        this.nome = nome;
        this.idade = idade;
        this.tempoDePena = tempoDePena;
        this.numeroCela = numeroCela;
    }

}
