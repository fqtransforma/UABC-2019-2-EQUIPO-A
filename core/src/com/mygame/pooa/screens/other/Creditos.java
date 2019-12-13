package com.mygame.pooa.screens.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class Creditos {
    private Stage stage;
    private Skin skin;
    private Window window;
    private TextButton closeWindow;

    private boolean isActive = false;

    public Creditos(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    private void init() {
        closeWindow = new TextButton("x", skin);
        closeWindow.setPosition(50, 50);

        window = new Window("", skin);
        window.setSize(Gdx.graphics.getWidth() / 7f * 3, Gdx.graphics.getHeight() / 7f * 6);
        window.setPosition(Gdx.graphics.getWidth() / 2f - window.getWidth() / 2, Gdx.graphics.getHeight() / 2f - window.getHeight() / 2);
        window.setMovable(false);
        window.add(closeWindow);

        stage.addActor(window);
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);
        if(isActive) {
            init();
        }
        stage.act();
        stage.draw();
    }
}
