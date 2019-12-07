package com.mygame.pooa.manager.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.screens.PlayScreen;

/**
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */

public class GameMenu {
    private MyGamePOOA game;

    private Stage stage;
    private Window window;
    private TextButton continuar;
    private TextButton salir;
/**
 * Menu principal del juego
 */
    public GameMenu(Skin skin, MyGamePOOA game) {
        this.game = game;
        stage = new Stage();

        window = new Window("", skin);
        window.setSize(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() * 3f / 4f);
        window.setPosition(Gdx.graphics.getWidth() / 2f - window.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - window.getHeight() / 2f);

        continuar = new TextButton("CONTINUAR", skin);
        continuar.setSize(window.getWidth() * 3f / 4f, 70);
        continuar.setPosition(window.getWidth() / 8, window.getHeight() - continuar.getHeight() * 2);

        salir = new TextButton("REGRESAR", skin);
        salir.setSize(window.getWidth() * 3f / 4f, 70);
        salir.setPosition(continuar.getX(), continuar.getY() - salir.getHeight() * 2);

        window.setLayoutEnabled(false);
        window.add(continuar, salir);
        stage.addActor(window);

        continuar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen.isPause = false;
            }
        });
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);
        if(window.getScaleX() < 1) window.setScale(window.getScaleX() + 0.1f);

        stage.act();
        stage.draw();

        if(salir.isPressed()) game.setScreen(game.home);
    }
}
