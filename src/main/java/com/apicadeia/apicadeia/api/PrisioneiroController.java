package com.apicadeia.apicadeia.api;

import com.apicadeia.apicadeia.model.Cela;
import com.apicadeia.apicadeia.model.Prisioneiro;
import com.apicadeia.apicadeia.repositories.CelaRepository;
import com.apicadeia.apicadeia.service.PrisioneiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/prisioneiros")
public class PrisioneiroController {

    @Autowired
    private PrisioneiroService prisioneiroService;

    @Autowired
    private CelaRepository celaRepository;

    public static class PrisioneiroDTO {
        public String nome;
        public int idade;
        public int tempoDePena;
        public int numeroCela;
    }

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Prisioneiro> criarPrisioneiro(@RequestBody PrisioneiroDTO prisioneiroDTO) {
        try {
            // Criar o novo prisioneiro com os dados do DTO
            Prisioneiro novoPrisioneiro = new Prisioneiro();
            novoPrisioneiro.setNome(prisioneiroDTO.nome);
            novoPrisioneiro.setIdade(prisioneiroDTO.idade);
            novoPrisioneiro.setTempoDePena(prisioneiroDTO.tempoDePena);

            // Buscar a cela no banco de dados usando o numeroCela
            Cela cela = celaRepository.findById(prisioneiroDTO.numeroCela)
                    .orElseThrow(() -> new IllegalArgumentException("Cela não encontrada"));

            // Associar a cela ao prisioneiro
            novoPrisioneiro.setCela(cela);

            // Salvar o prisioneiro e retornar a resposta
            return ResponseEntity.ok(prisioneiroService.criarPrisioneiro(novoPrisioneiro));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Prisioneiro>> listarTodosPrisioneiros() {
        try {
            return ResponseEntity.ok(prisioneiroService.listarTodosPrisioneiros());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Prisioneiro> buscarPrisioneiroPorId(@PathVariable int id) {
        return prisioneiroService.buscarPrisioneiroPorId(id)
                .map(ResponseEntity::ok) // Se encontrado, retorna 200 com o objeto
                .orElse(ResponseEntity.notFound().build()); // Caso contrário, retorna 404
    }


    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Prisioneiro> atualizarPrisioneiro(@PathVariable int id, @RequestBody Prisioneiro prisioneiroAtualizado) {
        try {
            return ResponseEntity.ok(prisioneiroService.atualizarPrisioneiro(id, prisioneiroAtualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrisioneiro(@PathVariable int id) {
        try {
            prisioneiroService.deletarPrisioneiro(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
