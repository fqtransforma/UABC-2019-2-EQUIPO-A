package com.mygame.pooa.screens.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.actors.Player.Player;
import com.mygame.pooa.manager.FileManager;

/**
 * Clase en la que se maneja la situación en la termina el juego.
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */
public class GameOver {
    private MyGamePOOA game;
    private Stage stage;
    private Window window;
    private Skin skin;

    private Image imagenBg;
    private ImageButton continuar;

    private FileManager.Scores scores;
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
    }

    /**
     * Crea el contenido que se mostrara
     */
    private void init() {
        isRender = false;

        scores = FileManager.readFile("scores.bin");
        window = new Window("", skin);
        window.setSize(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() * 5f / 6f);
        window.setPosition(Gdx.graphics.getWidth() / 2f - window.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - window.getHeight() / 2f);
        window.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("ui/gameOver.png"))));
        window.setMovable(false);
        window.setLayoutEnabled(false);

        continuar = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        continuar.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/gameOverContinuar.png")));
        continuar.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/gameOverContinuarH.png")));
        continuar.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/gameOverContinuarH.png")));
        continuar.setSize(324, 80);
        continuar.setPosition(window.getWidth() / 2 - continuar.getWidth() / 2, continuar.getHeight() / 7 * 6);

        Label score = new Label("" + Player.Points, skin);
        score.getStyle().fontColor.set(Color.BLACK);
        score.setPosition(window.getWidth() / 2f + 25, window.getHeight() / 2f - 25);

        Label position = new Label("0", skin);

        System.out.println(scores.size());

        position.setText( scores.addItem(Player.Points) + "" );
        position.setPosition(score.getX(), score.getY() - 55);
        window.add(continuar, score, position);

        FileManager.saveFile(scores, "scores.bin");

        imagenBg = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("ui/transparencia.png"))));
        imagenBg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imagenBg.setPosition(0, 0);

        stage.addActor(imagenBg);
        stage.addActor(window);
    }

    /**
     * Actualizacion del estado de la pantalla y las variables contenedoras
     * Los eventos de entrada afectaran solo a esta instancia y sus objetos contenidos (en su Stage)
     * @see Stage
     */
    public void render() {
        Gdx.input.setInputProcessor(stage);
        if(!isRender) {
            this.init();
            isRender = true;
        }

        if(window.getScaleX() < 1) window.setScale(window.getScaleX() + 0.1f);
        if(continuar.isPressed()) game.setScreen(game.home);

        stage.act();
        stage.draw();
    }
}
