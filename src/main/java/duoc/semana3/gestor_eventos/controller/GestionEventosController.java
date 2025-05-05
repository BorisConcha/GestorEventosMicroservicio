package duoc.semana3.gestor_eventos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import duoc.semana3.gestor_eventos.model.GestionEventos;
import duoc.semana3.gestor_eventos.service.GestionEventosService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    private static final Logger logger = LoggerFactory.getLogger(GestionEventosController.class);

    @GetMapping
    public CollectionModel<EntityModel<GestionEventos>> getAllEventos() {

        List<GestionEventos> eventos = gestionEventosService.getAllEventos();
        
        List<EntityModel<GestionEventos>> eventosResourse = eventos.stream()
            .map(evento -> EntityModel.of(evento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventosbyId(evento.getIdEvento())).withSelfRel()
                ))
            .collect(Collectors.toList());
        
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos());
        CollectionModel<EntityModel<GestionEventos>> data = CollectionModel.of(eventosResourse, linkTo.withRel("eventos"));

        return data;
    }
    

    @GetMapping("/{id_evento}")
    public EntityModel<GestionEventos> getEventosbyId(@PathVariable Long id_evento) {
        logger.info("Obteniendo el evento por el id: ", id_evento);
        Optional<GestionEventos> eventos = gestionEventosService.getEventosbyId(id_evento);
        
        if (eventos.isPresent()){
            return EntityModel.of(eventos.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventosbyId(id_evento)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));

        } else {

            throw new EventosNotFoundException("El evento con el id: " +id_evento+ " no existe en el sistema");
        }
    }

    @PostMapping
    public EntityModel<GestionEventos> createEvento(@RequestBody GestionEventos eventos) {

        GestionEventos crearEvento = gestionEventosService.createEvento(eventos);

        return EntityModel.of(crearEvento,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventosbyId(crearEvento.getIdEvento())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
    }


    @PutMapping("/{id_evento}")
    public EntityModel<GestionEventos> updateEvento(@PathVariable Long id_evento, @RequestBody GestionEventos eventos) {
        
        GestionEventos updateEventos = gestionEventosService.updateEvento(id_evento, eventos);
        return EntityModel.of(updateEventos,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventosbyId(id_evento)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
    }
    
    @DeleteMapping("/{id_evento}")
    public void deleteEvento(@PathVariable Long id_evento){

        gestionEventosService.deleteEvento(id_evento);
    }
}
