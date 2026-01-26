package com.esnap.backend.controller;

import com.esnap.backend.model.Noticia;
import com.esnap.backend.repository.NoticiaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//CRUD: CREATE, READ, UPDATE, DELETE

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    private final NoticiaRepository repository;

    public NoticiaController(NoticiaRepository repository) {
        this.repository = repository;
    }

    // Create a new news
    @PostMapping
    public Noticia create(@RequestBody Noticia noticia) {
        return repository.save(noticia);
    }

    // Read all news
    @GetMapping
    public List<Noticia> getAll() {
        return repository.findAll();
    }

    // Get news by ID
    @GetMapping("/{id}")
    public ResponseEntity<Noticia> getByID(@PathVariable Long id) {
        Optional<Noticia> noticia = repository.findById(id);

        // Si existe la devuelve (200 OK), si no existe devuelve error 404
        //If exists, return (200 OK), if not, return error 404
        return noticia.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT: UPDATE NEWS
    @PutMapping("/{id}")
    public ResponseEntity<Noticia> update(@PathVariable Long id, @RequestBody Noticia noticiaActualizada) {
        // Primero buscamos si existe la noticia que queremos cambiar
        // First, we are looking for the new we want to change
        return repository.findById(id)
                .map(noticiaExistente -> {
                    // If exists, update
                    noticiaExistente.setTitulo(noticiaActualizada.getTitulo());
                    noticiaExistente.setCuerpo(noticiaActualizada.getCuerpo());
                    noticiaExistente.setFecha(noticiaActualizada.getFecha());
                    noticiaExistente.setCategoria(noticiaActualizada.getCategoria());
                    noticiaExistente.setImagenUrl(noticiaActualizada.getImagenUrl());

                    // Save changes
                    Noticia guardada = repository.save(noticiaExistente);
                    return ResponseEntity.ok(guardada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // If it doesn't exist, error 404
    }

    // Delete news
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content (Success)
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }
}
