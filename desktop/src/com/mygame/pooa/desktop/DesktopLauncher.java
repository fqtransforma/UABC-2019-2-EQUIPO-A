package com.mygame.pooa.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygame.pooa.MyGamePOOA;

import java.awt.*;


/**
 *  <h2><b>SEPARADOR 3000 1.0</b></h2>
 *  <p><i>VIDEOJUEGO PARA ESCRITORIO DE CONCIENTIZACION SOBRE EL RECICLAJE</i></p> <br/>
 *  <b> UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA</b> <br/>
 *  <a href="http://uabc.mx" target="_blank">http://uabc.mx</a> <br/>
 *  @author Abraham Medina Carrillo
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 *  <br/><br/> <span class="simpleTagLabel">Correo electronico:</span><br/>
 *  <a href="https://mail.google.com/mail/?view=cm&fs=1&tf=1&to=abraham.medina.carrillo@uabc.edu.mx" target="_blank">abraham.medina.carrillo@uabc.edu.mx</a><br/>
 *  <a href="https://mail.google.com/mail/?view=cm&fs=1&tf=1&to=alejandro.gonzalez96@uabc.edu.mx" target="_blank">alejandro.gonzalez96@uabc.edu.mx</a><br/>
 *  <a href="https://mail.google.com/mail/?view=cm&fs=1&tf=1&to=emmanuel.rodriguez40@uabc.edu.mx" target="_blank">emmanuel.rodriguez40@uabc.edu.mx</a><br/>
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
