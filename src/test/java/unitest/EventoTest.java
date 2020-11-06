package unitest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Evento;

class EventoTest {


    @Test
    /**
     * Testea la correcta creacion de un Evento
     */
    public void testEvento(){
        int tipoEvento = Evento.COLOCA_BARCOS;
        int casilla = 10;
        Evento evento = new Evento(tipoEvento,casilla);
        assertTrue(evento.getEvento() == tipoEvento);
        assertTrue(evento.getIdCasilla() == casilla);
    }
}
