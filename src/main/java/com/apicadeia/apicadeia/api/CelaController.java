package com.apicadeia.apicadeia.api;

import com.apicadeia.apicadeia.model.Cela;
import com.apicadeia.apicadeia.service.CelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CelaController {

    @Autowired
    private CelaService celaService;

    @PostMapping(value = "/celas", consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Cela> criarCela(@RequestBody Cela cela) {
        try {
            Cela novaCela = celaService.criarCela(cela);
            return ResponseEntity.ok(novaCela);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/celas", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Cela>> listarTodasCelas() {
        try {
            List<Cela> celas = celaService.listarTodasCelas();
            return ResponseEntity.ok(celas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/buscar-cela", produces = {"application/json", "application/xml"})
    public ResponseEntity<Cela> buscarCelaPorNumero(@RequestParam int numero) {
        try {
            Optional<Cela> cela = celaService.buscarCelaPorNumero(numero);
            return cela.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/celas", consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<Cela> atualizarCela(@RequestParam int numero, @RequestBody Cela celaAtualizada) {
        try {
            Cela cela = celaService.atualizarCela(numero, celaAtualizada);
            if (cela != null) {
                return ResponseEntity.ok(cela);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping(value = "/celas", produces = {"application/json", "application/xml"})
    public ResponseEntity<Void> deletarCela(@RequestParam int numero) {
        try {
            celaService.deletarCela(numero);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
