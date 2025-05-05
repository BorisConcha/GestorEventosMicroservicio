package duoc.semana3.gestor_eventos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventosNotFoundException extends RuntimeException{
    
    public EventosNotFoundException(String message){
        super(message);
    }
}
