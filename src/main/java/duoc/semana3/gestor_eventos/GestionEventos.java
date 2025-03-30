package duoc.semana3.gestor_eventos;

public class GestionEventos {
    private int id_evento;
    private String fecha_inicio_evento;
    private String fecha_fin_evento;
    private String nombre_evento;
    private String tipo_evento;
    private int cantidad_participantes;
    private int cantidad_entradas;
    private String descripcion_evento;

    public GestionEventos(int id_evento, String fecha_inicio_evento, String fecha_fin_evento, String nombre_evento, String tipo_evento, int cantidad_participantes, int cantidad_entradas, String descripcion_evento){
        this.id_evento                  =   id_evento;
        this.fecha_inicio_evento        =   fecha_inicio_evento;
        this.fecha_fin_evento           =   fecha_fin_evento;
        this.nombre_evento              =   nombre_evento;
        this.tipo_evento                =   tipo_evento;
        this.cantidad_participantes     =   cantidad_participantes;
        this.cantidad_entradas          =   cantidad_entradas;
        this.descripcion_evento         =   descripcion_evento;
    }

    public int getIdEvento(){
        return id_evento;
    }

    public String getFechaInicioEvento(){
        return fecha_inicio_evento;
    }

    public String getFechaFinEvento(){
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
}
