/**
 *  SEPARADOR 3000 1.0
 *  APLICACION PARA ESCRITORIO DE ENTRETENIMIENTO Y CONCIENTIZACION SOBRE EL RECICLAJE
 *  AUTORES: Gonzalez Zepeda Alejandro, Medina Carrillo Abraham, Rodriguez Estrada Jesus Emmanuel
 *  CORREO ELECTRONICO {alejandro.gonzalez96, abraham.medina.carrillo, emmanuel.rodriguez40 }@uabc.edu.mx
 *  UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA
 *  http://uabc.mx
 */
package com.mygame.pooa.manager.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygame.pooa.actors.Player;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Contiene todos los elementos de UI que se veran en la pantalla de juego, como las vidas, los puntos, la opcion para abrir el menu de pause
 */

public class Hub {
    public static int objectDestroy = 0;

    private Stage stage;
    private Skin skin;
    private Label label;
    private TextButton menu;
    private Label puntos;

    private Button button;
    private Touchpad touchpad;

    /**
     * @param stage Escenario donde se colocaran los elementos de UI
     */

    public Hub(Stage stage) {
        this.stage = stage;
        skin = new Skin(Gdx.files.internal("ui/normal.json"));

        button = new Button(skin);
        button.setSize(100, 100);
        button.setPosition(Gdx.graphics.getWidth() - button.getWidth() * 1.5f, button.getHeight() / 2);

        touchpad = new Touchpad(15f, skin);
        touchpad.setBounds(0, 0, 100, 100);
        touchpad.setPosition(touchpad.getWidth() / 2, touchpad.getHeight() / 2);

        label = new Label("Vidas: 0", skin);
        label.getStyle().fontColor.set(Color.WHITE);
        label.setPosition(15, stage.getHeight() - label.getHeight() * 1.5f);

        puntos = new Label("Puntos: 0", skin);
        puntos.getStyle().fontColor.set(Color.WHITE);
        puntos.setPosition(stage.getWidth() / 2f - puntos.getWidth() / 2, stage.getHeight() - puntos.getHeight() * 1.5f);

        menu = new TextButton("Menu", skin);
        menu.setPosition(stage.getWidth() - menu.getWidth() * 1.5f, stage.getHeight() - menu.getHeight() * 1.5f);

        stage.addActor(puntos);
        stage.addActor(button);
        stage.addActor(touchpad);
        stage.addActor(label);
        stage.addActor(menu);
        eventHandler();
    }

    private void eventHandler() {
        menu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen.isPause = true;
            }
        });
    }

    /**
     * Actualizacion de las variables a presentar en el HUB
     */

    public void update() {
        Gdx.input.setInputProcessor(stage);

        puntos.setText("Puntos: " + Player.Points);
        label.setText("Vidas: " + objectDestroy);
        Player.PressClose = button.isPressed();
    }

    /**
     * @return Posicion porcentual del Joystick
     */

    public Vector2 getPositionTouchPad() {
        Player.PressClose = button.isPressed();
        return new Vector2( touchpad.getKnobPercentX(), touchpad.getKnobPercentY() );
    }

    /**
     * @return Estilos graficos de UI
     */

    public Skin getSkin() {
        return skin;
    }
}
