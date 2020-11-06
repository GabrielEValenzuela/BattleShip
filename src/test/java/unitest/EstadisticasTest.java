package unitest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Evento;
import model.Tablero;
import view.Display;
import view.EstadisticasView;

class EstadisticasTest {

	Tablero tablero ;
	Display display ;
	EstadisticasView estadisticas ;
	
	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero() ;
		display = new Display(tablero) ;
		estadisticas = new EstadisticasView(display) ;	
	}

	@Test
	void testUpdateEventoColocarBarcos() { //Jugador1
		Evento evento = new Evento(Evento.COLOCA_BARCOS, 1) ;
		estadisticas.update(evento);
		assertEquals(1, estadisticas.get_barcosColocadosJug1()) ;
	}
	@Test
	void testUpdateEventoRealizarDisparos() { //Jugador1
		Evento evento = new Evento(Evento.REALIZA_DISPARO, 1) ;
		estadisticas.update(evento);
		assertEquals(1, estadisticas.get_disparosRealizadosJug1()) ;
	}
	@Test
	void testUpdateEventoDestruirBarcos() { //Jugador0
		Evento evento = new Evento(Evento.DESTRUYE_BARCO, 0) ;
		estadisticas.update(evento);
		assertEquals(1, estadisticas.get_barcosDestruidosJug0()) ;
	}
	@Test
	void testUpdateReiniciar() {
		Evento evento = new Evento(Evento.REINICIA_JUEGO, 1) ;
		estadisticas.update(evento);
		assertEquals(0, estadisticas.get_barcosColocadosJug0()) ;
		assertEquals(0, estadisticas.get_barcosColocadosJug1()) ;
		assertEquals(0, estadisticas.get_barcosDestruidosJug0()) ;
		assertEquals(0, estadisticas.get_barcosDestruidosJug1()) ;
		assertEquals(0, estadisticas.get_disparosRealizadosJug0()) ;
		assertEquals(0, estadisticas.get_disparosRealizadosJug1()) ;
	}

}
