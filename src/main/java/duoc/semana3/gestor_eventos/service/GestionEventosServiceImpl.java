package duoc.semana3.gestor_eventos.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duoc.semana3.gestor_eventos.model.GestionEventos;
import duoc.semana3.gestor_eventos.repository.GestionEventosRepository;

@Service
public class GestionEventosServiceImpl implements GestionEventosService{
    @Autowired
    private GestionEventosRepository gestioneventosRepository;

    private static final Logger logger = LoggerFactory.getLogger(GestionEventosServiceImpl.class);

    @Override
    public List<GestionEventos> getAllEventos(){
        logger.info("Buscando los eventos del sistema");
        try{

            List<GestionEventos> eventos = gestioneventosRepository.findAll();
            logger.info("Encontrados con exito:",eventos);
            return eventos;

        }catch (Exception e){
            logger.info("Error al buscar los eventos:",e);
            throw e;
        }
    }

    @Override
    public Optional<GestionEventos> getEventosbyId(Long id_evento){
        logger.info("Buscando el evento en el sistema con el id:",id_evento);
        try{

            Optional<GestionEventos> evento = gestioneventosRepository.findById(id_evento);
            logger.info("Evento encontrado:",evento);
            return evento;

        }catch (Exception e){
            logger.info("Error al buscar el evento:",e);
            throw e;
        }
        
    }

    @Override
    public GestionEventos createEvento(GestionEventos eventos){
        logger.info("Creando un evento en el sistema:",eventos);
        try{

            GestionEventos nuevo_evento = gestioneventosRepository.save(eventos);
            logger.info("Evento creado correctamente en el sistema:",nuevo_evento);
            return nuevo_evento;

        }catch (Exception e){
            logger.info("Error al crear un nuevo evento en el sistema:",e);
            throw e;
        }
    }

    @Override
    public GestionEventos updateEvento(Long id_evento,GestionEventos eventos){
        logger.info("Buscando el evento a modificar en el sistema");
        if(gestioneventosRepository.existsById(id_evento)){

            eventos.setIdEvento(id_evento);
            GestionEventos evento = gestioneventosRepository.save(eventos);
            logger.info("Evento modificado correctamente en el sistema:",evento);
            return evento;

        }else{
            logger.info("El evento a modificar con el id: "+id_evento+" no existe en el sistema");
            return null;
        }
    }

    @Override
    public void deleteEvento(Long id_evento){
        logger.info("Borrando el evento en el sistema con el id: ",id_evento);
        try{

            gestioneventosRepository.deleteById(id_evento);
            logger.info("Evento eliminado correctamente en el sistema");

        }catch (Exception e){
            logger.info("Error al eliminar un evento en el sistema",e);
            throw e;
        }
    }
}
