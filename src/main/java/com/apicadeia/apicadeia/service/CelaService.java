package com.apicadeia.apicadeia.service;

import com.apicadeia.apicadeia.model.Cela;
import com.apicadeia.apicadeia.repositories.CelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CelaService {

    @Autowired
    private CelaRepository celaRepository;

    public Cela criarCela(Cela cela) {
        return celaRepository.save(cela);
    }

    public List<Cela> listarTodasCelas() {
        return celaRepository.findAll();
    }

    public Cela buscarCelaPorNumero(int numero) {
        return celaRepository.findById(numero)
                .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));
    }

    public Cela atualizarCela(int numero, Cela celaAtualizada) {
        Cela celaExistente = celaRepository.findById(numero)
                .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));

        // Atualizar os campos necessários
        celaExistente.setNumero(celaAtualizada.getNumero());

        return celaRepository.save(celaExistente);
    }

    public void deletarCela(int numero) {
        if (!celaRepository.existsById(numero)) {
            throw new IllegalArgumentException("Cela não encontrada");
        }
        celaRepository.deleteById(numero);
    }
}
