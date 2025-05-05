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

        eventos.setNombreEvento("La Momia");
        eventos.setFechaInicio("1985");
        eventos.setFechaFin("1985");
        eventos.setTipoEvento("Stephen Sommers");
        eventos.setCantParticipantes(1985);
        eventos.setCantEntradas(1985);
        eventos.setDescripcionEvento("Rick O'Connell y un compañero descubren las ruinas de Hamunaptra. Después vuelven al mismo lugar con una egiptóloga y su hermano. Allí coinciden con un grupo de americanos que provocan la resurrección de la momia de un diabólico sacerdote egipcio.");

        when(gestionEventosRepository.save(any())).thenReturn(eventos);

        GestionEventos resultado = gestionEventosServiceImplMock.createEvento(eventos);

        assertEquals("La Momia", resultado.getNombreEvento());

    }

    @Test
    public void updateEventoTest() {

        GestionEventos eventos = new GestionEventos();

        eventos.setNombreEvento("Torneo de ping pong regional de prueba");
        eventos.setFechaInicio("20/06/2023");
        eventos.setFechaFin("25/06/2023");
        eventos.setTipoEvento("Competencia");
        eventos.setCantParticipantes(10000);
        eventos.setCantEntradas(10000);
        eventos.setDescripcionEvento("Este es un torneo donde la gente podra jugar ping pong");

        when(gestionEventosRepository.save(any())).thenReturn(eventos);

        GestionEventos resultado = gestionEventosServiceImplMock.createEvento(eventos);

        assertEquals("Torneo de ping pong regional de prueba", resultado.getNombreEvento());

    }
}
