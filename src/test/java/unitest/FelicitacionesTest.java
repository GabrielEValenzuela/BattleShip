package unitest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Barco;
import model.Evento;
import model.Jugador;
import model.Tablero;
import view.Felicitaciones;

class FelicitacionesTest {
	
	Tablero tablero ;
	Felicitaciones felicitaciones;
	Evento evento ;
	
	@BeforeEach
	void setUp() throws Exception {
	  tablero		 = new Tablero();
	  felicitaciones = new Felicitaciones(tablero);
	  evento = new Evento(Evento.TERMINO_PARTIDA, 1) ;
	}
	
	
	@Test
	void testFelicitarPerdio() {
		
		ArrayList<Barco> barcos0 = tablero.getBarcosJug0();
	    
	    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
	        barcos0.get(i).setVida(0);
	      }
	    felicitaciones.felicitar( tablero.encontrarGanador().getNombre());
	    
	    assertEquals("Mal ahi " + tablero.getJugador0().getNombre() 
	            + " aprieta reiniciar para intentarlo de nuevo", felicitaciones.getMensaje().getText()) ;
		
	}
	
	@Test
	void testFelicitarGano() {
		
		ArrayList<Barco> barcos1 = tablero.getBarcosJug1();
	    
	    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
	        barcos1.get(i).setVida(0);
	      }
	    felicitaciones.felicitar( tablero.encontrarGanador().getNombre());
	    
	    assertEquals("Felicitaciones " + tablero.encontrarGanador().getNombre() + " ganaste!", felicitaciones.getMensaje().getText()) ;
		
	}
	
	@Test
	void testUpdate() {
		
		ArrayList<Barco> barcos0 = tablero.getBarcosJug0();
	    
	    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
	        barcos0.get(i).setVida(0);
	      }
		
		
		felicitaciones.update(evento);
		 assertEquals("Mal ahi " + tablero.getJugador0().getNombre() 
		            + " aprieta reiniciar para intentarlo de nuevo", felicitaciones.getMensaje().getText()) ;
	    
	   
		
		
		
	}

}
