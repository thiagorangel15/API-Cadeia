package com.apicadeia.apicadeia.service;

import com.apicadeia.apicadeia.model.Cela;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CelaService {

    private List<Cela> celas = new ArrayList<>();



    public Cela criarCela(Cela cela) {
        celas.add(cela);
        return cela;
    }


    public List<Cela> listarTodasCelas() {
        return celas;
    }

    public Optional<Cela> buscarCelaPorNumero(int numero) {
        return celas.stream().filter(cela -> cela.getNumero() == numero).findFirst();
    }


    public Cela atualizarCela(int numero, Cela celaAtualizada) {
        Optional<Cela> celaExistente = buscarCelaPorNumero(numero);
        if (celaExistente.isPresent()) {
            Cela cela = celaExistente.get();
            return cela;
        }
        return null;
    }


    public void deletarCela(int numero) {
        celas.removeIf(cela -> cela.getNumero() == numero);
    }
}
