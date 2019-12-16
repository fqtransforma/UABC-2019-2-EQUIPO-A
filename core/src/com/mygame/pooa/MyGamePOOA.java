/**
 * Videojuego para escritorio, creado por alumnos de la <a href="http://www.uabc.mx/">Universidad Autonoma de Baja California</a> con la finalidad de concientizar de manera didactiva y entretenida el reciclaje.
 * Nombre del videojuego: Separador 3000
 * Integrantes del equipo desarrollador:
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 * Supervisor del proyecto: Dr. Manuel Castanon Puga
 * Institucion / Organizacion receptora: <a href="http://www.fqt.org.mx/" target="_blank">Fundacion Que Transforma</a>
 */

package com.mygame.pooa;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.pooa.manager.AssetsManager;
import com.mygame.pooa.screens.Home;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Manejador de pantallas, se controla el estado de las mismas, cual se mira y renderiza, cual se destruye
 * ademas de contener el manejador de recursos y {@link com.badlogic.gdx.graphics.g2d.Batch} para dibujar.
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
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
