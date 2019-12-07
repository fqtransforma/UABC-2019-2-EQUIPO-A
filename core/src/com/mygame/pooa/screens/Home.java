/**
 *  SEPARADOR 3000 1.0
 *  APLICACION PARA ESCRITORIO DE ENTRETENIMIENTO Y CONCIENTIZACION SOBRE EL RECICLAJE
 *  AUTORES: Gonzalez Zepeda Alejandro, Medina Carrillo Abraham, Rodriguez Estrada Jesus Emmanuel
 *  CORREO ELECTRONICO {alejandro.gonzalez96, abraham.medina.carrillo, emmanuel.rodriguez40 }@uabc.edu.mx
 *  UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA
 *  http://uabc.mx
 */

package com.mygame.pooa.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.pooa.MyGamePOOA;

/**
 * Pantalla de inicio
 */

public class Home extends GameScreen {
    private float timerChange = 5f;

    private Stage stage;
    private Skin skin;

    private TextButton playGame;
    private TextButton exitGame;

    public Home(MyGamePOOA game) {
        super(game);
    }

    /** Opcion de jugar y cerrar que aparecen a la hora que el juego se coloca en modo pausa*/

    @Override
    public void show() {
        PlayScreen.resetTimeSleep();
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("ui/normal.json"));

        playGame = new TextButton("JUGAR", skin);
        playGame.setSize(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 7f);
        playGame.setPosition(playGame.getWidth() / 2, 500);

        exitGame = new TextButton("CERRAR", skin);
        exitGame.setSize(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 7f);
        exitGame.setPosition(exitGame.getWidth() / 2, 300);


        stage.addActor(playGame);
        stage.addActor(exitGame);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if(exitGame.isPressed()) Gdx.app.exit();
        if(playGame.isPressed()) game.setScreen(game.playscreen);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
