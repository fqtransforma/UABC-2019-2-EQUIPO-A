package com.mygame.pooa.screens.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Pantalla de Menu
 *  @author Abraham Medina Carrillo
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 *
 */

public class GameMenu {
    private MyGamePOOA game;

    private Stage stage;
    private Window window;
    private ImageButton continuar;
    private ImageButton salir;
    private Slider controlVolume;
    private Label controlVolumeIndicator;
    private Image imagenBg;
/**
 * Menu principal del juego
 */
    public GameMenu(Skin skin, MyGamePOOA game) {
        this.game = game;
        stage = new Stage();

        window = new Window("", skin);
        window.setSize(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() * 3f / 4f);
        window.setPosition(Gdx.graphics.getWidth() / 2f - window.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - window.getHeight() / 2f);
        window.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("ui/pausaWindow.png"))));

        /** SECCION DE LA OPCIONES */
        continuar = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        continuar.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/continuar.png")));
        continuar.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/continuarHover.png")));
        continuar.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/continuarHover.png")));
        continuar.setSize(479 * 3f / 5, 153 * 3f / 5);
        continuar.setPosition(window.getWidth() / 2 - continuar.getWidth() / 2, window.getHeight() / 2 + continuar.getHeight() / 2f * 1.5f);

        salir = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        salir.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/regresar.png")));
        salir.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/regresarHover.png")));
        salir.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/regresarHover.png")));
        salir.setSize(479 * 3f / 5, 153 * 3f / 5);
        salir.setPosition(continuar.getX(), continuar.getY() - salir.getHeight() * 1.2f);

        controlVolume = new Slider(0f, 1f, 0.01f, false, skin);
        controlVolume.setSize(window.getWidth() / 2f, 5f);
        controlVolume.setPosition(window.getWidth() / 2f - controlVolume.getWidth() / 2f, 100);
        controlVolume.setValue(PlayScreen.soundVolume);
        controlVolume.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture("ui/sliderLine.png")));
        controlVolume.getStyle().knob = new TextureRegionDrawable(new TextureRegion(new Texture("ui/sliderPush.png")));

        controlVolumeIndicator = new Label("" + (int)(PlayScreen.soundVolume * 100), salir.getSkin());
        controlVolumeIndicator.getStyle().fontColor = Color.BLACK;
        controlVolumeIndicator.setPosition(window.getWidth() / 2 - controlVolumeIndicator.getWidth() / 2f, controlVolume.getY() + controlVolume.getHeight() + 50);
        controlVolumeIndicator.setAlignment(Align.center);

        window.setLayoutEnabled(false);
        window.add(controlVolumeIndicator, controlVolume, continuar, salir);

        imagenBg = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("ui/transparencia.png"))));
        imagenBg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imagenBg.setPosition(0, 0);

        stage.addActor(imagenBg);
        stage.addActor(window);

        continuar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen.isPause = false;
            }
        });
    }
    /**
     * Se encarga de actualizar el contenido en el escenario actual, creando una ventana para una mejor visualizacion
     */
    public void render() {
        Gdx.input.setInputProcessor(stage); /** Eventos que suceden en escenarios */
        if(window.getScaleX() < 1) window.setScale(window.getScaleX() + 0.1f); /** Efecto POPUP en la ventana */

        stage.act(); /** Actualiza el escenario sin dubujar */
        stage.draw(); /** Dibuja los cambios */

        PlayScreen.soundVolume = controlVolume.getValue();
        controlVolumeIndicator.setText((int)(controlVolume.getValue() * 100) + "");

        if(salir.isPressed()) game.setScreen(game.home);
    }
}
