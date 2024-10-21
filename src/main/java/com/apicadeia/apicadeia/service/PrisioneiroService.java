package com.apicadeia.apicadeia.service;

import com.apicadeia.apicadeia.model.Cela;
import com.apicadeia.apicadeia.model.Prisioneiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrisioneiroService {

    @Autowired
    private CelaService celaService;

    private List<Prisioneiro> prisioneiros = new ArrayList<>();
    private int nextId = 1;


    public Prisioneiro criarPrisioneiro(String nome, int idade, int tempoDePena, int numeroCela) {

        Prisioneiro prisioneiro = new Prisioneiro();
        prisioneiro.setId(nextId++);
        prisioneiro.setNome(nome);
        prisioneiro.setIdade(idade);
        prisioneiro.setTempoDePena(tempoDePena);


        Cela cela = celaService.buscarCelaPorNumero(numeroCela)
                .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));


        prisioneiro.setNumeroCela(cela.getNumero());


        cela.adicionarPrisioneiro(prisioneiro);

        prisioneiros.add(prisioneiro);

        return prisioneiro;
    }




    public List<Prisioneiro> listarTodosPrisioneiros() {
        return prisioneiros;
    }

    public Optional<Prisioneiro> buscarPrisioneiroPorId(int id) {
        return prisioneiros.stream().filter(prisioneiro -> prisioneiro.getId() == id).findFirst();
    }


    public Prisioneiro atualizarPrisioneiro(int id, Prisioneiro prisioneiroAtualizado) {
        Optional<Prisioneiro> prisioneiroExistente = buscarPrisioneiroPorId(id);
        if (prisioneiroExistente.isPresent()) {
            Prisioneiro prisioneiro = prisioneiroExistente.get();


            prisioneiro.setNome(prisioneiroAtualizado.getNome());
            prisioneiro.setIdade(prisioneiroAtualizado.getIdade());
            prisioneiro.setTempoDePena(prisioneiroAtualizado.getTempoDePena());


            Cela novaCela = celaService.buscarCelaPorNumero(prisioneiroAtualizado.getNumeroCela())
                    .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));


            Cela celaAntiga = celaService.buscarCelaPorNumero(prisioneiro.getNumeroCela())
                    .orElseThrow(() -> new IllegalArgumentException("Cela antiga não encontrada"));
            celaAntiga.removerPrisioneiro(prisioneiro);


            prisioneiro.setNumeroCela(novaCela.getNumero());


            novaCela.adicionarPrisioneiro(prisioneiro);

            return prisioneiro;
        }
        return null;
    }


    // Delete
    public void deletarPrisioneiro(int id) {

        Prisioneiro prisioneiro = prisioneiros.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Prisioneiro não encontrado"));


        int numeroCela = prisioneiro.getNumeroCela();


        Cela cela = celaService.buscarCelaPorNumero(numeroCela)
                .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));

        cela.removerPrisioneiro(prisioneiro);


        prisioneiros.remove(prisioneiro);
    }

}
