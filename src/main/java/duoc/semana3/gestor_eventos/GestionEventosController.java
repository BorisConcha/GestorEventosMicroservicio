package duoc.semana3.gestor_eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class GestionEventosController {
    private List<GestionEventos> eventos = new ArrayList<>();

    public GestionEventosController(){
        eventos.add(new GestionEventos(1, "15/04/2025", "16/04/2025", "Rock en Vivo", "Concierto", 5000, 7500, "Festival de bandas de rock alternativo"));
        eventos.add(new GestionEventos(2, "20/05/2025", "20/05/2025", "Sinfónica Nacional", "Concierto", 1200, 1500, "Presentación de música clásica en el teatro municipal"));
        eventos.add(new GestionEventos(3, "05/05/2025", "05/05/2025", "Final de Copa regional sub 17", "Deportivo", 30000, 35000, "Final del campeonato regional de fútbol"));
        eventos.add(new GestionEventos(4, "20/08/2025", "21/08/2025", "Convención de Medicina", "Conferencia", 500, 600, "Encuentro de profesionales médicos para discutir avances en el campo"));
        eventos.add(new GestionEventos(5, "28/09/2025", "28/09/2025", "Concurso de Parrilleros", "Gastronomía", 2000, 2500, "Competencia de asadores con los mejores chefs de la región"));
    }

    @GetMapping("/eventos")
    public List<GestionEventos> getEventos(){
        return eventos;
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<?> getEventobyId(@PathVariable int id){
        Map<Integer, GestionEventos> mapEventos = new HashMap<>();
    
        for (GestionEventos evento : eventos) {
            mapEventos.put(evento.getIdEvento(), evento);
        }

        GestionEventos resultado = mapEventos.get(id);
    
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: El evento con el numero de id " + id + " no existe en el sistema, favor ingresar un numero valido");
        }
    
        return ResponseEntity.ok(resultado);


    }
    
}
