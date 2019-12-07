/**
 * Separador 3000
 * Juego que consiste en separar basura objetos relacionandolos a su color pensado para consientizar a cerca del reciclaje
 * AUTORES: Gonzalez Zepeda Alejandro, Medina Carrillo Abraham, Rodriguez Estrada Jesus Emmanuel
 * CORREO ELECTRÓNICO: {alejandro.gonzalez96, }@uabc.edu.mx
 * Universidad Autónoma del Estado de Baja California
 * http://www.uabc.mx
 */

package com.mygame.pooa.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygame.pooa.MyGamePOOA;

import java.awt.*;

/**
 * Clase principal
 */

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		config.fullscreen = true;
		new LwjglApplication(new MyGamePOOA(), config);
	}
}
