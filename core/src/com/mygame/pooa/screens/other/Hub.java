package com.mygame.pooa.screens.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Contiene todos los elementos de UI que se veran en la pantalla de juego, como las vidas, los puntos, la opcion para abrir el menu de pause
 *  @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 */

public class Hub {
    public static int objectDestroy = 5;
    private int vidaAux;

    private Stage stage;
    private Skin skin;
    private ImageButton menu;

    private Table vidas;
    private Image vidasBg;

    /**
     * @param stage Escenario donde se colocaran los elementos de UI
     */
    public Hub(Stage stage) {
        this.stage = stage;
        vidaAux = objectDestroy;
        skin = new Skin(Gdx.files.internal("ui/normal.json"));

        menu = new ImageButton(skin);
        menu.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/menuGame.png")));
        menu.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/menuGameHover.png")));
        menu.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/menuGameHover.png")));
        menu.setSize(233 / 3f, 203 / 3f);
        menu.setPosition(stage.getWidth() - menu.getWidth() * 1.2f, stage.getHeight() - menu.getHeight() * 1.2f);

        vidasBg = new Image(new Texture("ui/vidas.png"));
        vidasBg.setSize(460 / 3f * 2, 110 / 3f * 2);
        vidasBg.setPosition(15, stage.getHeight() - vidasBg.getHeight() * 1.2f);

        vidas = new Table(skin);
        vidas.setSize(vidasBg.getWidth() / 10f * 6, vidasBg.getHeight() / 2f);
        vidas.setPosition(vidasBg.getX() + vidasBg.getWidth() / 10f * 3, vidasBg.getY() + vidasBg.getHeight() / 2 - vidas.getHeight() / 2f - 5);

        for(int k=0; k<objectDestroy; k++) {
            Image img = new Image(new Texture("ui/vida.png"));
            img.setSize(70 / 4f, 64 / 4f);
            vidas.add(img);
        }

        stage.addActor(vidasBg);
        stage.addActor(vidas);
        stage.addActor(menu);
        eventHandler();
    }

    /**
     * Eventos en espera de accionarse
     */
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

        if(vidaAux != objectDestroy && vidaAux>=1) {
            Image img = new Image(new Texture("ui/vidaPerdida.png"));
            img.setSize(70 / 4f, 64 / 4f);
            vidas.getCells().get(vidaAux-1).setActor(img);
            vidaAux = objectDestroy;
        }
    }

    /**
     * @return Estilos graficos predeterminados de UI
     */
    public Skin getSkin() {
        return skin;
    }
}
