package com.mygame.pooa.screens;

import com.badlogic.gdx.Screen;
import com.mygame.pooa.MyGamePOOA;

/**
 * Clase abstracta para el manejo de la pantalla en las diferentes clases.
 *  @author Abraham Medina Carrillo
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 */

public abstract class GameScreen implements Screen {
    public MyGamePOOA game;

    /**
     * @param game Manegador de pantallas
     */

    public GameScreen(MyGamePOOA game){
        this.game = game;
    }

    /**
     * Cada que se muestra la pantalla como principal se llama ha esta funcion
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    /**
     * @param width Nuevo ancho del escenario al redimencionarse
     * @param height Nuevo alto del escenario al redimencionarse
     */
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    /**
     * Se llama antes de destruir la pantalla
     */
    @Override
    public void dispose() {

    }
}
