package com.br.enginejava.mundo;

public class Camera {

	public static int y , x;
	
	public static int Clamp(int inicio, int minimo , int maximo) {
		if (inicio < minimo){
			inicio = minimo ;
		}
		if (inicio > maximo) {
			inicio = maximo;
		}
		return inicio;
	}
	
}
