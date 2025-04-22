package duoc.semana3.gestor_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import duoc.semana3.gestor_eventos.model.GestionEventos;

public interface GestionEventosRepository extends JpaRepository<GestionEventos, Long>{
    
}
