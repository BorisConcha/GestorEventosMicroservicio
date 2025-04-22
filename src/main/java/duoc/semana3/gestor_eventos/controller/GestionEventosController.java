package duoc.semana3.gestor_eventos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import duoc.semana3.gestor_eventos.model.GestionEventos;
import duoc.semana3.gestor_eventos.service.GestionEventosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class GestionEventosController {
    @Autowired
    private GestionEventosService gestionEventosService;

    @GetMapping
    public List<GestionEventos> getAllEventos() {
        return gestionEventosService.getAllEventos();
    }
    

    @GetMapping("/{id_evento}")
    public Optional<GestionEventos> getEventosbyId(@PathVariable Long id_evento) {
        return gestionEventosService.getEventosbyId(id_evento);
    }

    @PostMapping
    public GestionEventos createEvento(@RequestBody GestionEventos eventos) {
        return gestionEventosService.createEvento(eventos);
    }

    @PutMapping("/{id_evento}")
    public GestionEventos updateEvento(@PathVariable Long id_evento, @RequestBody GestionEventos eventos) {
        
        return gestionEventosService.updateEvento(id_evento, eventos);
    }
    
    @DeleteMapping("/{id_evento}")
    public void deleteEvento(@PathVariable Long id_evento){

        gestionEventosService.deleteEvento(id_evento);
    }
}
