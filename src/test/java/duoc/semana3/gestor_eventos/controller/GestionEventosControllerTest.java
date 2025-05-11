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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import duoc.semana3.gestor_eventos.model.GestionEventos;
import duoc.semana3.gestor_eventos.service.GestionEventosService;
import duoc.semana3.gestor_eventos.service.GestionEventosServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    public void createEventoTest() {

        GestionEventos eventoRequest = new GestionEventos();
        eventoRequest.setNombreEvento("Evento de prueba");
        eventoRequest.setFechaInicio("12/10/2024");
        eventoRequest.setFechaFin("14/10/2024");
        eventoRequest.setTipoEvento("Conferencia");
        eventoRequest.setCantParticipantes(5000);
        eventoRequest.setCantEntradas(3500);
        eventoRequest.setDescripcionEvento("Este es un evento de prueba");
        
        GestionEventos eventoCreado = new GestionEventos();
        eventoCreado.setIdEvento(1L);
        eventoCreado.setNombreEvento("Evento de prueba");
        eventoCreado.setFechaInicio("12/10/2024");
        eventoCreado.setFechaFin("14/10/2024");
        eventoCreado.setTipoEvento("Conferencia");
        eventoCreado.setCantParticipantes(5000);
        eventoCreado.setCantEntradas(3500);
        eventoCreado.setDescripcionEvento("Este es un evento de prueba");
        
        when(gestionEventosService.createEvento(any(GestionEventos.class))).thenReturn(eventoCreado);
        
        EntityModel<GestionEventos> response = gestionEventosController.createEvento(eventoRequest);
        
        verify(gestionEventosService).createEvento(any(GestionEventos.class));
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        
        GestionEventos resultado = response.getContent();
        assertEquals(1L, resultado.getIdEvento());
        assertEquals("Evento de prueba", resultado.getNombreEvento());
        assertEquals("12/10/2024", resultado.getFechaInicio());
        assertEquals("14/10/2024", resultado.getFechaFin());
        assertEquals("Conferencia", resultado.getTipoEvento());
        assertEquals(5000, resultado.getCantParticipantes());
        assertEquals(3500, resultado.getCantEntradas());
        assertEquals("Este es un evento de prueba", resultado.getDescripcionEvento());
        
        assertTrue(response.hasLinks());
        assertNotNull(response.getLink("self").orElse(null));
        assertNotNull(response.getLink("all-eventos").orElse(null));
    }

    @Test
    public void updateEventoTest() {

        GestionEventos eventoActualizado = new GestionEventos();
        eventoActualizado.setIdEvento(1L);
        eventoActualizado.setNombreEvento("Evento actualizado");
        eventoActualizado.setFechaInicio("15/10/2024");
        eventoActualizado.setFechaFin("17/10/2024");
        eventoActualizado.setTipoEvento("Seminario");
        eventoActualizado.setCantParticipantes(3000);
        eventoActualizado.setCantEntradas(2500);
        eventoActualizado.setDescripcionEvento("Este es un evento actualizado");
        
        GestionEventos eventoOriginal = new GestionEventos();
        eventoOriginal.setIdEvento(1L);
        eventoOriginal.setNombreEvento("Evento de prueba");
        eventoOriginal.setFechaInicio("12/10/2024");
        eventoOriginal.setFechaFin("14/10/2024");
        eventoOriginal.setTipoEvento("Conferencia");
        eventoOriginal.setCantParticipantes(5000);
        eventoOriginal.setCantEntradas(3500);
        eventoOriginal.setDescripcionEvento("Este es un evento de prueba");
        
        when(gestionEventosService.getEventosbyId(1L)).thenReturn(Optional.of(eventoOriginal));
        
        when(gestionEventosService.updateEvento(eq(1L), any(GestionEventos.class))).thenReturn(eventoActualizado);
        
        EntityModel<GestionEventos> response = gestionEventosController.updateEvento(1L, eventoActualizado);
        
        verify(gestionEventosService).updateEvento(eq(1L), any(GestionEventos.class));
        
        assertNotNull(response);
        assertNotNull(response.getContent());
        
        GestionEventos resultado = response.getContent();
        assertEquals(1L, resultado.getIdEvento());
        assertEquals("Evento actualizado", resultado.getNombreEvento());
        assertEquals("15/10/2024", resultado.getFechaInicio());
        assertEquals("17/10/2024", resultado.getFechaFin());
        assertEquals("Seminario", resultado.getTipoEvento());
        assertEquals(3000, resultado.getCantParticipantes());
        assertEquals(2500, resultado.getCantEntradas());
        assertEquals("Este es un evento actualizado", resultado.getDescripcionEvento());
        
        assertTrue(response.hasLinks());
        assertNotNull(response.getLink("self").orElse(null));
        assertNotNull(response.getLink("all-eventos").orElse(null));
    }
}
