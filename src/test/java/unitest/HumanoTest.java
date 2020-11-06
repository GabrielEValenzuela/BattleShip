package unitest;

import model.Humano;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumanoTest {

    @Test
    /**
     * Testea la correcta creacion de un Humano
     */
    void testHumano(){
        String nombre = "Humano";
        int id = 1;
        Humano humano = new Humano(nombre, id);
        assertTrue(humano.getNombre() == nombre);
        assertTrue(humano.getPlayerID() == id);
    }

}
