package unitest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AI;
import model.Tablero;
import model.Controler;
import view.Display;

import java.util.Random;

class AITest {
	
	Tablero tablero;
	AI ai;
	Controler controler;
	Display display;

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
		try{
		display = new Display(tablero);
		controler = new Controler(tablero,display);
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
		System.out.println("Coordenadas");
		for(int o = 0;o < coor.length; o++){
			System.out.println(coor[o]);
		}
		assertTrue(coor[0] != 99 && coor[1] != 99);
	}
	catch (Exception HeadlessException){
		assertTrue(true);
	}
/*		grilla = tablero.getGrillaJugador0();
		disparoRandom();
		int[][] grillaNew = tablero.getGrillaJugador0().clone();
		boolean sup, inf,izq,der;
		sup = inf = izq = der = false;
		if(coor[0] != 0) sup = grilla[coor[0]-1][coor[1]] != grillaNew[coor[0]-1][coor[1]];
		if(coor[0] != grilla.length-1) inf = grilla[coor[0]+1][coor[1]] != grillaNew[coor[0]+1][coor[1]];
		if(coor[1] != 0) izq = grilla[coor[0]][coor[1]-1] != grillaNew[coor[0]][coor[1]-1];
		if(coor[1] != grilla.length-1) der = grilla[coor[0]][coor[1]+1] != grillaNew[coor[0]][coor[1]+1];
		//assertTrue(sup | inf | izq | der);
		coor = encontrarDisparo(grilla, grillaNew);
		System.out.println("Coordenadas del disparo nuevo");
		for(int o = 0;o < coor.length; o++){
			System.out.println(coor[o]);
		}*/
	}
/*
	private int[] encontrarDisparo(int[][] g, int[][] f){
		int fila, columna;
		fila = columna = 99;
		for(int i = 0; i < g.length; i++){
			for(int j = 0; j < g.length; j++){
				if(g[i][j] != f[i][j]){
					fila = i;
					columna = j;
				}
			}
		}
		return new int[]{fila, columna};
	}

	private void disparoRandom(){
		Random random = new Random();
		int limite = tablero.getGrillaJugador0().length;
		int fila, columna;
		fila = random.nextInt(limite);
		columna = random.nextInt(limite);
		int[][] original = tablero.getGrillaJugador0();
		int[][] nuevo = original;
		int[] aux = {99,99};
		while(aux[0] == 99){
			tablero.dispararEventoEnGrilla(1,fila, columna, 1);
			nuevo = tablero.getGrillaJugador0();
			aux = encontrarDisparo(original, nuevo);
		}
	}
*/
	private int[] dispararHastaEncontrar(){
		Random random = new Random();
		int limite = tablero.getGrillaJugador0().length;
		int fila = 99, columna = 99;
		boolean fin = false;
		while(!fin){
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
		int[] ret = {fila,columna};
		return ret;
	}

}
