package duoc.semana3.gestor_eventos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import duoc.semana3.gestor_eventos.model.GestionEventos;
import duoc.semana3.gestor_eventos.repository.GestionEventosRepository;

@ExtendWith(MockitoExtension.class)
public class GestionEventosServiceTest {
    
    @InjectMocks
    private GestionEventosServiceImpl gestionEventosServiceImplMock;

    @Mock
    GestionEventosRepository gestionEventosRepository;

    @Test
    public void createEventoTest() {

        GestionEventos eventos = new GestionEventos();

        eventos.setNombreEvento("Conferencia de prueba");
        eventos.setFechaInicio("15/07/2020");
        eventos.setFechaFin("15/07/2020");
        eventos.setTipoEvento("Conferencia");
        eventos.setCantParticipantes(8000);
        eventos.setCantEntradas(7000);
        eventos.setDescripcionEvento("Este es una conferencia de prueba");


        when(gestionEventosRepository.save(any())).thenReturn(eventos);

        GestionEventos resultado = gestionEventosServiceImplMock.createEvento(eventos);

        assertEquals("Conferencia de prueba", resultado.getNombreEvento());

    }

}
