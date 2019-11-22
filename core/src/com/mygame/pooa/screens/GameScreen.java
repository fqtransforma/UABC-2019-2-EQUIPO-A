package com.mygame.pooa.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygame.pooa.MyGamePOOA;

public abstract class GameScreen implements Screen {
    public static final float PPM = 32f;

    public MyGamePOOA game;

    public GameScreen(MyGamePOOA game){
        this.game = game;
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

    }

    /**
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     */
    @Override
    public void pause() {

    }

    /**
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
