package com.apicadeia.apicadeia.api;

import com.apicadeia.apicadeia.model.Prisioneiro;
import com.apicadeia.apicadeia.service.PrisioneiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PrisioneiroController {

    @Autowired
    private PrisioneiroService prisioneiroService;


    public static class PrisioneiroDTO {
        public String nome;
        public int idade;
        public int tempoDePena;
        public int numeroCela;


    }


    @PostMapping(value = "/prisioneiros", consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Prisioneiro> criarPrisioneiro(@RequestBody PrisioneiroDTO prisioneiroDTO) {
        try {

            Prisioneiro novoPrisioneiro = prisioneiroService.criarPrisioneiro(prisioneiroDTO.nome, prisioneiroDTO.idade,
                    prisioneiroDTO.tempoDePena, prisioneiroDTO.numeroCela);
            return ResponseEntity.ok(novoPrisioneiro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping(value = "/prisioneiros", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Prisioneiro>> listarTodosPrisioneiros() {
        try {
            List<Prisioneiro> prisioneiros = prisioneiroService.listarTodosPrisioneiros();
            return ResponseEntity.ok(prisioneiros);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping(value = "/buscar-prisioneiro", produces = {"application/json", "application/xml"})
    public ResponseEntity<Prisioneiro> buscarPrisioneiroPorId(@RequestParam int id) {
        try {
            Optional<Prisioneiro> prisioneiro = prisioneiroService.buscarPrisioneiroPorId(id);
            return prisioneiro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping(value = "/prisioneiros", consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Prisioneiro> atualizarPrisioneiro(@RequestParam int id, @RequestBody Prisioneiro prisioneiroAtualizado) {
        try {
            Prisioneiro prisioneiro = prisioneiroService.atualizarPrisioneiro(id, prisioneiroAtualizado);
            if (prisioneiro != null) {
                return ResponseEntity.ok(prisioneiro);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping(value = "/prisioneiros", produces = {"application/json", "application/xml"})
    public ResponseEntity<Void> deletarPrisioneiro(@RequestParam int id) {
        try {
            prisioneiroService.deletarPrisioneiro(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
