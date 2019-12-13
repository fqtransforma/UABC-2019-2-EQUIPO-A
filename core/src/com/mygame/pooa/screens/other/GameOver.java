package com.mygame.pooa.screens.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.actors.Player.Player;
import com.mygame.pooa.manager.FileManager;

/**
 * Clase en la que se maneja la situación en la termina el juego.
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 *
 */

public class GameOver {
    private MyGamePOOA game;

    private Stage stage;
    private Window window;
    private Skin skin;
    private Label points;
    private TextButton button;

    private boolean isRender = false;

    /**
     * @param skin Estilo grafico a utilizar
     * @param game Clase principal
     * @see Skin
     * @see GameMenu
     */

    public GameOver(Skin skin, MyGamePOOA game) {
        this.game = game;
        this.skin = skin;
        stage = new Stage();

        this.init();
    }

    private void init() {
        window = new Window("", skin);
        window.setSize(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() * 3f / 4f);
        window.setPosition(Gdx.graphics.getWidth() / 2f - window.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - window.getHeight() / 2f);

        Label label = new Label("NOMBRE DE USUARIO", skin);
        label.setColor(Color.BLACK);
        label.setSize(label.getWidth(), 70);
        label.setPosition(window.getWidth() / 2f - label.getWidth() / 2f, window.getHeight() - label.getHeight() * 2);

        points = new Label("0", skin);
        points.setColor(Color.BLACK);
        points.setFontScale(1.5f);
        points.setSize(points.getWidth(), 70);
        points.setPosition(window.getWidth() / 2f - points.getWidth(), window.getY() + points.getHeight() * 2.5f);

        TextField textField = new TextField("", skin);
        textField.setColor(Color.LIGHT_GRAY);
        textField.setSize(window.getWidth() * 3f / 4f, 70);
        textField.setPosition(window.getWidth() / 8, label.getY() - textField.getHeight());

        button = new TextButton("CONTINUAR", skin);
        button.setColor(Color.GRAY);
        button.setSize(textField.getWidth(), textField.getHeight());
        button.setPosition(textField.getX(), window.getY() + button.getHeight());
        button.setDisabled(false);

        window.setLayoutEnabled(false);
        window.add(textField, label, button, points);

        FileManager.Scores scores = FileManager.readFile("scores.txt");
        scores.add(Player.Points);
        FileManager.saveFile(scores, "scores.txt");
        Label temp = new Label("MAXSCORE: " + scores.maxSize() + "", skin);
        temp.setColor(Color.BLACK);
        temp.setSize(label.getWidth(), 70);
        temp.setPosition(window.getWidth() / 2f - temp.getWidth() / 2f, 60);
        window.add(temp);

        stage.addActor(window);
    }

    /**
     * Actualizacion del estado de la pantalla y las variables contenedoras
     * Los eventos de entrada afectaran solo a esta instancia y sus objetos contenidos (en su Stage)
     * @see Stage
     */

    public void render() {
        if(!isRender) {
            this.init();
            isRender = true;
        }

        Gdx.input.setInputProcessor(stage);
        points.setText("" + Player.Points);
        points.setPosition(window.getWidth() / 2f - points.getWidth(), window.getY() + points.getHeight() * 2.5f);

        if(window.getScaleX() < 1) window.setScale(window.getScaleX() + 0.1f);
        if(button.isPressed() && !button.isDisabled()) {
            game.setScreen(game.home);
            isRender = false;
        }

        stage.act();
        stage.draw();
    }
}