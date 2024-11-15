package com.apicadeia.apicadeia.service;

import com.apicadeia.apicadeia.model.Prisioneiro;
import com.apicadeia.apicadeia.model.Cela;
import com.apicadeia.apicadeia.repositories.CelaRepository;
import com.apicadeia.apicadeia.repositories.PrisioneiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrisioneiroService {

    @Autowired
    private PrisioneiroRepository prisioneiroRepository;

    @Autowired
    private CelaRepository celaRepository;

    public Prisioneiro criarPrisioneiro(Prisioneiro prisioneiro) {

        return prisioneiroRepository.save(prisioneiro);
    }

    public List<Prisioneiro> listarTodosPrisioneiros() {
        return prisioneiroRepository.findAll();
    }

    public Optional<Prisioneiro> buscarPrisioneiroPorId(int id) {
        return prisioneiroRepository.findById(id);
    }

    public Prisioneiro atualizarPrisioneiro(int id, Prisioneiro prisioneiroAtualizado) {
        Prisioneiro prisioneiroExistente = prisioneiroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prisioneiro não encontrado"));

        // Atualiza os campos do prisioneiro existente
        prisioneiroExistente.setNome(prisioneiroAtualizado.getNome());
        prisioneiroExistente.setIdade(prisioneiroAtualizado.getIdade());
        prisioneiroExistente.setTempoDePena(prisioneiroAtualizado.getTempoDePena());

        if (prisioneiroAtualizado.getCela() != null) {
            Cela cela = celaRepository.findById(prisioneiroAtualizado.getCela().getNumero())
                    .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));
            prisioneiroExistente.setCela(cela);
        }

        return prisioneiroRepository.save(prisioneiroExistente);
    }

    public void deletarPrisioneiro(int id) {
        if (!prisioneiroRepository.existsById(id)) {
            throw new IllegalArgumentException("Prisioneiro não encontrado");
        }
        prisioneiroRepository.deleteById(id);
    }
}
