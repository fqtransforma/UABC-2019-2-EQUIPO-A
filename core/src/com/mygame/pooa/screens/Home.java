package com.mygame.pooa.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.screens.other.Creditos;

/**
 * Pantalla de inicio
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */

public class Home extends GameScreen {
    private Stage stage;
    private Skin skin;

    private TextButton playGame;
    private TextButton exitGame;
    private TextButton creditos;
    private Creditos _creditos;

    public Home(MyGamePOOA game) {
        super(game);
    }

    /** Opcion de jugar y cerrar que aparecen a la hora que el juego se coloca en modo pausa*/

    @Override
    public void show() {
        PlayScreen.resetTimeSleep();
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("ui/normal.json"));
        _creditos = new Creditos(stage, skin);

        final float heightUse = Gdx.graphics.getHeight() / 10f;
        final float widthUse = Gdx.graphics.getWidth() / 4f;

        playGame = new TextButton("JUGAR", skin);
        playGame.setSize(widthUse, heightUse);
        playGame.setPosition(Gdx.graphics.getWidth() / 2f - playGame.getWidth() / 2, heightUse * 7 - playGame.getHeight() / 2);

        creditos = new TextButton("CREDITOS", skin);
        creditos.setSize(widthUse, heightUse);
        creditos.setPosition(Gdx.graphics.getWidth() / 2f - creditos.getWidth() / 2, heightUse * 5 - creditos.getHeight() / 2);

        exitGame = new TextButton("CERRAR", skin);
        exitGame.setSize(widthUse, heightUse);
        exitGame.setPosition(Gdx.graphics.getWidth() / 2f - exitGame.getWidth() / 2, heightUse * 3 - exitGame.getHeight() / 2);

        stage.addActor(playGame);
        stage.addActor(exitGame);
        stage.addActor(creditos);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!_creditos.isActive()) Gdx.input.setInputProcessor(stage);

        stage.act();
        stage.draw();

        if(exitGame.isPressed()) Gdx.app.exit();
        if(playGame.isPressed()) game.setScreen(game.playscreen);
        if(creditos.isPressed()) _creditos.setActive(true);
        _creditos.render();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
