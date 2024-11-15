package com.apicadeia.apicadeia.api;

import com.apicadeia.apicadeia.model.Cela;
import com.apicadeia.apicadeia.service.CelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/celas")
public class CelaController {

    @Autowired
    private CelaService celaService;

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Cela> criarCela(@RequestBody Cela cela) {
        try {
            Cela novaCela = celaService.criarCela(cela);
            return ResponseEntity.ok(novaCela);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Cela>> listarTodasCelas() {
        try {
            return ResponseEntity.ok(celaService.listarTodasCelas());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{numero}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Cela> buscarCelaPorNumero(@PathVariable int numero) {
        try {
            Cela cela = celaService.buscarCelaPorNumero(numero);
            return ResponseEntity.ok(cela);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{numero}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Cela> atualizarCela(@PathVariable int numero, @RequestBody Cela celaAtualizada) {
        try {
            Cela cela = celaService.atualizarCela(numero, celaAtualizada);
            return ResponseEntity.ok(cela);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletarCela(@PathVariable int numero) {
        try {
            celaService.deletarCela(numero);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
