/**
 **  @author Juan Manuel Carrera García
 **/

 package main;

import logica.GestorBD;
import gui.VentanaGUI;

public class Main {
	public static void main(String[] args) {
		GestorBD.conexion();
		new VentanaGUI();
	}
}
