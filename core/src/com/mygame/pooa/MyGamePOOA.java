/**
 *  SEPARADOR 3000 1.0
 *  APLICACION PARA ESCRITORIO DE ENTRETENIMIENTO Y CONCIENTIZACION SOBRE EL RECICLAJE
 *  AUTORES: Gonzalez Zepeda Alejandro, Medina Carrillo Abraham, Rodriguez Estrada Jesus Emmanuel
 *  CORREO ELECTRONICO {alejandro.gonzalez96, abraham.medina.carrillo, emmanuel.rodriguez40 }@uabc.edu.mx
 *  UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA
 *  http://uabc.mx
 */

package com.mygame.pooa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.pooa.manager.AssetsManager;
import com.mygame.pooa.screens.Home;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Manejador de pantallas, se controla el estado de las mismas, cual se mira y renderiza, cual se destruye
 * ademas de contener el manejador de recursos y {@link com.badlogic.gdx.graphics.g2d.Batch} para dibujar
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */

public class MyGamePOOA extends Game {
	private SpriteBatch batch;
	public AssetsManager assetsManager;

	public Home home;
	public PlayScreen playscreen;

	/**
	 * Constructor
	 */
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetsManager = new AssetsManager();

		playscreen = new PlayScreen(this);
		home = new Home(this);

		setScreen(home);
	}

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
	public void dispose () {
		home.dispose();
        playscreen.dispose();
        assetsManager.dispose();
		batch.dispose();
	}
}
