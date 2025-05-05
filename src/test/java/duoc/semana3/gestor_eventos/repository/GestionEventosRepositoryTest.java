package duoc.semana3.gestor_eventos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import duoc.semana3.gestor_eventos.model.GestionEventos;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GestionEventosRepositoryTest {
    
    @Autowired
    private GestionEventosRepository gestionEventosRepository;

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


        GestionEventos resultado = gestionEventosRepository.save(eventos);

        assertNotNull(resultado.getIdEvento());

        assertEquals("Conferencia de prueba", resultado.getNombreEvento());

    }
}
