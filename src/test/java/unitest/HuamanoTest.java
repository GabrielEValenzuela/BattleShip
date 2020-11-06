package unitest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Humano;

class HuamanoTest {

	Humano humano ;
	
    @BeforeEach
	void setUp() throws Exception{
	    humano = new Humano();
	}
	
	
	@Test
	void testSetNombre() {
		humano.setNombre("gabi");
		assertEquals("gabi", humano.getNombre()) ;
	}
	
	@Test
	void testSetPlayerID() {
		humano.setPlayerID(2);
		assertEquals(2, humano.getPlayerID()) ;
	}
	
	@Test
	void testSetBarcosColocados() {
		humano.setBarcosColocadosJugador(6);
		assertEquals(6, humano.getBarcosColocadosJugador()) ;
	}
	

}
