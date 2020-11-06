package unitest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AI;
import model.Tablero;

import java.util.Random;

class AITest {
	
	Tablero tablero;
	AI ai;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		ai = new AI(tablero);
	}

	@Test
	/**
	 * Testea si los valores utilizados para la seleccion de casilla de la AI son validos
	 */
	void testRealizarTurno() {
		int[] coordenadas = ai.realizarTurno();
		if(coordenadas[0] != 3) {
			if(coordenadas[0] != 1) fail("El disparo de la AI no simulo un click primario ni secundario");
		}
		assert (coordenadas[1] >= 0 && coordenadas [1] <= 10);
		assert (coordenadas[2] >= 0 && coordenadas [2] <= 10);
	}

	@Test
	/**
	 * Testea el comportamiento de la AI posterior a la colocacion de los barcos
	 */
	void testRealizarTurnoDisparo(){
		for(int i = 0; i < tablero.getGrillaJugador0().length; i++){
			tablero.dispararEventoEnGrilla(1,i,0,0);
		}
		//A este punto deberian haberse colocado todos los barcos
		//Chequeamos que la AI haya colocado los barcos en su grilla
		int[][] grilla = tablero.getGrillaJugador1();
		boolean hayBarcos = false;
		for(int i = 0; i < grilla.length; i++){
			for(int j = 0; j < grilla.length; j++){
				if(grilla[i][j] == tablero.BARCO){
					hayBarcos = true;
				}
			}
		}
		assertTrue(hayBarcos);
		//Disparamos hasta que la AI le pegue a un barco
		int[] coor = dispararHastaEncontrar();
		for(int o = 0;o < coor.length; o++){
			System.out.println(coor[o]);
		}
	}

	private int[] dispararHastaEncontrar(){
		Random random = new Random();
		int limite = tablero.getGrillaJugador0().length;
		int fila = 0, columna = 0;
		boolean fin = false;
		while(!fin){
			System.out.println("Imprimimos ambas matrices\n");
			tablero.printMatriz();
			fila = random.nextInt(limite);
			columna = random.nextInt(limite);
			int id = random.nextInt(2);
			tablero.dispararEventoEnGrilla(1,fila, columna, id);
			int[][] grilla = tablero.getGrillaJugador0();
			for(int i = 0; i < grilla.length; i++){
				for(int j = 0; j < grilla.length; j++){
					if(grilla[i][j] == tablero.BARCO_HIT){
						fin = true;
						fila = i;
						columna = j;
					}
				}
			}
		}
		return new int[]{fila, columna};
	}

}
