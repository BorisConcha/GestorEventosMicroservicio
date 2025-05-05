package duoc.semana3.gestor_eventos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import duoc.semana3.gestor_eventos.model.GestionEventos;
import duoc.semana3.gestor_eventos.service.GestionEventosService;
import duoc.semana3.gestor_eventos.service.GestionEventosServiceImpl;

import static org.mockito.Mockito.*;

@WebMvcTest(GestionEventosController.class)
public class GestionEventosControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GestionEventosService gestionEventosService;

    @MockBean
    private GestionEventosServiceImpl gestionEventosServiceImplMock;

    @InjectMocks
    private GestionEventosController gestionEventosController;


    @Test
    public void obtenerEventosTest() throws Exception{

        GestionEventos eventos1 = new GestionEventos();
        eventos1.setIdEvento(1L);
        eventos1.setNombreEvento("Evento de prueba");
        eventos1.setFechaInicio("12/10/2024");
        eventos1.setFechaFin("14/10/2024");
        eventos1.setTipoEvento("Conferencia");
        eventos1.setCantParticipantes(5000);
        eventos1.setCantEntradas(3500);
        eventos1.setDescripcionEvento("Este es un evento de prueba");

        GestionEventos eventos2 = new GestionEventos();
        eventos2.setIdEvento(2L);
        eventos2.setNombreEvento("Otro evento de prueba");
        eventos2.setFechaInicio("01/03/2022");
        eventos2.setFechaFin("05/03/2022");
        eventos2.setTipoEvento("Concierto");
        eventos2.setCantParticipantes(10000);
        eventos2.setCantEntradas(9500);
        eventos2.setDescripcionEvento("Este es un concierto con musica de prueba");

        List<GestionEventos> eventos = Arrays.asList(eventos1, eventos2);
        when(gestionEventosServiceImplMock.getAllEventos()).thenReturn(eventos);

        mockMvc.perform(MockMvcRequestBuilders.get("/eventos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.eventosList[0].nombreEvento", Matchers.is("Evento de prueba")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.eventosList[1].nombreEvento", Matchers.is("Otro evento de prueba")));

    }

    @Test
    public void getEventosbyIdTest() {

        GestionEventos eventos = new GestionEventos();
        eventos.setIdEvento(1L);
        eventos.setNombreEvento("Evento de prueba");
        eventos.setFechaInicio("12/10/2024");
        eventos.setFechaFin("14/10/2024");
        eventos.setTipoEvento("Conferencia");
        eventos.setCantParticipantes(5000);
        eventos.setCantEntradas(3500);
        eventos.setDescripcionEvento("Este es un evento de prueba");
        
        when(gestionEventosService.getEventosbyId(1L)).thenReturn(Optional.of(eventos));
        
        EntityModel<GestionEventos> response = gestionEventosController.getEventosbyId(1L);
        
        verify(gestionEventosService).getEventosbyId(1L);

        assertNotNull(response);
        assertNotNull(response.getContent());
        assertEquals(1L, response.getContent().getIdEvento());
        assertEquals("Evento de prueba", response.getContent().getNombreEvento());
        assertEquals("Conferencia", response.getContent().getTipoEvento());
        
        assertTrue(response.hasLinks());
        assertTrue(response.getLinks().hasLink("self"));

    }
}
