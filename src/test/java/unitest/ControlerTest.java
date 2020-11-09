package unitest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Tablero;
import view.Display;
import model.Controler;

import java.awt.Button;
import java.awt.event.MouseEvent;


class ControlerTest {

    Tablero tablero;
    Display display;
    Controler controler;

    @BeforeEach
    /**
     * Setea todo lo necesario para los otros tests
     */
    public void beforeTestControler(){
        try{
        tablero = new Tablero();
        display = new Display(tablero);
        controler = new Controler(tablero,display);
        }
        catch (Exception HeadlessException){
            assertTrue(true);
        }
    }

    @Test
    /**
     *Testea la notificacion de un evento
     */
    public void TestNotifyEvent(){
        int boton = 1;
        MouseEvent primario = new MouseEvent(new Button(),1,1,1,1,1,1,false,1);
        MouseEvent secundario = new MouseEvent(new Button(),1,1,1,1,1,1,false,3);
        controler.notifyEvent(primario,0,0,0);
        controler.notifyEvent(secundario,0,2,0);
        int[][] grilla = tablero.getGrillaJugador0();
        assertTrue(grilla[0][0] == tablero.BARCO);
        assertTrue(grilla[1][0] == tablero.BARCO);
        assertTrue(grilla[0][2] == tablero.BARCO);
        assertTrue(grilla[0][3] == tablero.BARCO);
    }
}
