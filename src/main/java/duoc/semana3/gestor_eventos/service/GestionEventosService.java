package duoc.semana3.gestor_eventos.service;

import java.util.List;
import java.util.Optional;

import duoc.semana3.gestor_eventos.model.GestionEventos;

public interface GestionEventosService {
    List<GestionEventos> getAllEventos();
    Optional<GestionEventos> getEventosbyId(Long id_evento);
    GestionEventos createEvento(GestionEventos eventos);
    GestionEventos updateEvento(Long id_evento, GestionEventos eventos);
    void deleteEvento(Long id_evento);
}
