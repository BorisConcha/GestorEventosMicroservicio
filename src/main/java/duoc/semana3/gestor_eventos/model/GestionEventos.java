package duoc.semana3.gestor_eventos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "eventos")
public class GestionEventos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventos_seq")
    @SequenceGenerator(name = "eventos_seq", sequenceName = "eventos_seq", allocationSize = 1)
    @Column(name = "id_evento")
    private Long id_evento;

    @NotNull
    @Size(max=12)
    @Column(name = "fecha_inicio_evento")
    private String fecha_inicio_evento;

    @NotNull
    @Size(max=12)
    @Column(name = "fecha_fin_evento")
    private String fecha_fin_evento;
    
    @Column(name = "nombre_evento")
    private String nombre_evento;

    @Column(name = "tipo_evento")
    private String tipo_evento;

    @NotNull
    @Column(name = "cantidad_participantes")
    private int cantidad_participantes;

    @NotNull
    @Column(name = "cantidad_entradas")
    private int cantidad_entradas;

    @Column(name = "descripcion_evento")
    private String descripcion_evento;

    public Long getIdEvento(){
        return id_evento;
    }

    public String getFechaInicio(){
        return fecha_inicio_evento;
    }

    public String getFechaFin(){
        return fecha_fin_evento;
    }

    public String getNombreEvento(){
        return nombre_evento;
    }

    public String getTipoEvento(){
        return tipo_evento;
    }

    public int getCantParticipantes(){
        return cantidad_participantes;
    }

    public int getCantEntradas(){
        return cantidad_entradas;
    }

    public String getDescripcionEvento(){
        return descripcion_evento;
    }

    public void setIdEvento(Long id_evento){ 
        this.id_evento = id_evento;
    }

    public void setFechaInicio(String fecha_inicio_evento){ 
        this.fecha_inicio_evento = fecha_inicio_evento;
    }

    public void setFechaFin(String fecha_fin_evento){ 
        this.fecha_fin_evento = fecha_fin_evento;
    }

    public void setNombreEvento(String nombre_evento){ 
        this.nombre_evento = nombre_evento;
    }

    public void setTipoEvento(String tipo_evento){ 
        this.tipo_evento = tipo_evento;
    }

    public void setCantParticipantes(int cantidad_participantes){ 
        this.cantidad_participantes = cantidad_participantes;
    }

    public void setCantEntradas(int cantidad_entradas){ 
        this.cantidad_entradas = cantidad_entradas;
    }

    public void setDescripcionEvento(String descripcion_evento){ 
        this.descripcion_evento = descripcion_evento;
    }
}
