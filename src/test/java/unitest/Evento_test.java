package unitest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Evento;

class Evento_test {

	Evento evento ;

   @BeforeEach
		void setUp() throws Exception{
		evento = new Evento(evento.COLOCA_BARCOS, 10);
    }
	
	
	@Test
	void testSetEvento() {
		evento.setEvento(evento.DESTRUYE_BARCO);
		assertEquals(30, evento.getEvento()) ;
	}
	
	@Test
	void testSetJugador() {
		evento.setIdJugador(2);;
		assertEquals(2, evento.getIdCasilla()) ;
	}


}
