package com.mygame.pooa.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.pooa.MyGamePOOA;

/**
 * Pantalla de inicio
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */

public class Home extends GameScreen {
    private Stage stage;
    private Image image;

    private ImageButton playGame;
    private CheckBox info;
    private CheckBox setting;

    private ImageButton exitGame;
    private CheckBox muteSound;
    private Table table;

    private Window windowControlHelp;
    private ImageButton controlHelp;
    private ImageButton closeControlHelp;

    private Window windowCreditos;
    private Image imageCreditos;
    private ImageButton closeCreditos;
    private Image logoFQT;

    public Home(MyGamePOOA game) {
        super(game);
    }

    @Override
    public void show() {
        PlayScreen.resetTimeSleep();
        stage = new Stage(new ScreenViewport());
        image = new Image(new Texture("images/backgroundMain.jpg"));
        stage.addActor(image);

        final float heightUse = Gdx.graphics.getHeight() / 10f;
        final float widthUse = Gdx.graphics.getWidth() / 4f;

        playGame = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        playGame.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/boton.png")));
        playGame.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/botonHover.png")));
        playGame.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/botonHover.png")));
        playGame.setSize(479 * 3f / 4, 153 * 3f / 4);
        playGame.setPosition(Gdx.graphics.getWidth() / 2f - playGame.getWidth() / 2, heightUse * 6 - playGame.getHeight() * 4f / 5);

        controlHelp = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        controlHelp.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/score.png")));
        controlHelp.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/scoreHover.png")));
        controlHelp.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/scoreHover.png")));
        controlHelp.setSize(479 / 2f, 153 / 2f);
        controlHelp.setPosition(Gdx.graphics.getWidth() / 2f - controlHelp.getWidth() / 2, heightUse * 4 - controlHelp.getHeight() / 2);

        info = new CheckBox("", new Skin(Gdx.files.internal("ui/normal.json")));
        info.getStyle().checkboxOff = new TextureRegionDrawable(new TextureRegion(new Texture("ui/info.png")));
        info.getStyle().checkboxOver = new TextureRegionDrawable(new TextureRegion(new Texture("ui/infoHover.png")));
        info.getStyle().checkboxOn = new TextureRegionDrawable(new TextureRegion(new Texture("ui/infoHover.png")));
        info.setSize(149 / 3f * 2, 129 / 3f * 2);
        info.setPosition(Gdx.graphics.getWidth() - info.getWidth() - widthUse / 8, info.getHeight() / 2);

        setting = new CheckBox("", new Skin(Gdx.files.internal("ui/normal.json")));
        setting.getStyle().checkboxOff = new TextureRegionDrawable(new TextureRegion(new Texture("ui/settings.png")));
        setting.getStyle().checkboxOver = new TextureRegionDrawable(new TextureRegion(new Texture("ui/settingsHover.png")));
        setting.getStyle().checkboxOn = new TextureRegionDrawable(new TextureRegion(new Texture("ui/settingsHover.png")));
        setting.setSize(149 / 3f * 2, 129 / 3f * 2);
        setting.setPosition(widthUse / 8, setting.getHeight() / 2);
        setting.getImage().setSize(setting.getWidth(), setting.getHeight());

        table = new Table(new Skin(Gdx.files.internal("ui/normal.json")));
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("ui/optionSetting.png"))));
        table.setSize(128 / 3f * 2, 270 / 3f * 2);
        table.setPosition(setting.getX() + setting.getWidth() / 2 - table.getWidth() / 2, setting.getY() + setting.getHeight() / 7 * 6);

        exitGame = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        exitGame.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/exitGame.png")));
        exitGame.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/exitGameHover.png")));
        exitGame.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/exitGameHover.png")));

        muteSound = new CheckBox("", new Skin(Gdx.files.internal("ui/normal.json")));
        muteSound.getStyle().checkboxOn = new TextureRegionDrawable(new TextureRegion(new Texture("ui/music.png")));
        muteSound.getStyle().checkboxOff = new TextureRegionDrawable(new TextureRegion(new Texture("ui/musicHover.png")));
        if(PlayScreen.soundVolume!=1) muteSound.setChecked(false);
        else muteSound.setChecked(true);

        table.add(exitGame);
        table.row();
        table.add(new Label("", table.getSkin()));
        table.row();
        table.add(muteSound);
        table.row();
        table.add(new Label("", table.getSkin()));

        logoFQT = new Image(new Texture("logos/fqt.png"));
        logoFQT.setPosition(Gdx.graphics.getWidth() - logoFQT.getWidth() - 10f, Gdx.graphics.getHeight() - logoFQT.getHeight() - 5f);

        stage.addActor(table);
        stage.addActor(info);
        stage.addActor(setting);
        stage.addActor(controlHelp);
        stage.addActor(playGame);
        stage.addActor(logoFQT);
        creditosWindow();
        controllerWindow();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(playGame.isPressed()) game.setScreen(game.playscreen);
        if(exitGame.isPressed()) Gdx.app.exit();

        if(muteSound.isChecked() && muteSound.isPressed()) PlayScreen.soundVolume = 1;
        else if(!muteSound.isChecked() && muteSound.isPressed()) PlayScreen.soundVolume = 0;
        if(PlayScreen.soundVolume != 1) muteSound.setChecked(false);
        if(PlayScreen.soundVolume != 0) muteSound.setChecked(true);

        if(setting.isChecked()) table.setVisible(true);
        else table.setVisible(false);

        if(info.isChecked())  {
            imageCreditos.setVisible(true);
            windowCreditos.setVisible(true);
        } else {
            imageCreditos.setVisible(false);
            windowCreditos.setVisible(false);
        }
        if(closeCreditos.isPressed()) info.setChecked(false);
        if(windowCreditos.isTouchFocusTarget()) info.setChecked(false);


        if(controlHelp.isPressed()) windowControlHelp.setVisible(true);
        if(windowControlHelp.isVisible()) imageCreditos.setVisible(true);

        if(closeControlHelp.isPressed()) windowControlHelp.setVisible(false);

        stage.act();
        stage.draw();
    }

    /**
     * Creacion de la ventana emergente para los creditos
     */
    private void creditosWindow() {
        imageCreditos = new Image(new Texture("ui/transparencia.png"));
        imageCreditos.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imageCreditos.setVisible(false);
        windowCreditos = new Window("", new Skin(Gdx.files.internal("ui/normal.json")));
        windowCreditos.setSize(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        windowCreditos.setPosition(Gdx.graphics.getWidth() / 2f - windowCreditos.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - windowCreditos.getHeight() / 2f);
        windowCreditos.setMovable(false);
        windowCreditos.setModal(true);
        windowCreditos.setVisible(false);
        windowCreditos.setLayoutEnabled(false);
        windowCreditos.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("ui/transparencia.png"))));

        closeCreditos = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        closeCreditos.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/closeWindowHover.png")));
        closeCreditos.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/closeWindow.png")));
        closeCreditos.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/closeWindow.png")));
        closeCreditos.setSize(25, 25);
        closeCreditos.setPosition(windowCreditos.getWidth() - closeCreditos.getWidth(), windowCreditos.getHeight() - closeCreditos.getHeight());

        Table credito = new Table();
        windowCreditos.getStyle().titleFontColor.set(Color.WHITE);
        Label autores = new Label("AUTORES\nAbraham Medina Carrillo\nAlejandro Gonzalez Zepeda\nJesus Emmanuel Rogriguez Estrada", windowCreditos.getSkin());
        autores.setAlignment(Align.center);
        credito.add(autores);
        credito.row();
        credito.add(new Label("", windowCreditos.getSkin()));
        credito.row();

        Label fundacion = new Label("FUNDACION\nFundacion Que Transforma", windowCreditos.getSkin());
        fundacion.setAlignment(Align.center);
        credito.add(fundacion);
        credito.row();
        credito.add(new Label("", windowCreditos.getSkin()));
        credito.row();

        Label agradecimiento = new Label("SUPERVISOR\nDr. Manuel Castanon Puga", windowCreditos.getSkin());
        agradecimiento.setAlignment(Align.center);
        credito.add(agradecimiento);

        ScrollPane scrollPane = new ScrollPane(credito);
        scrollPane.setPosition(0, 0);
        scrollPane.setSize(windowCreditos.getWidth(), windowCreditos.getHeight());

        windowCreditos.add(scrollPane);
        windowCreditos.add(closeCreditos);

        stage.addActor(imageCreditos);
        stage.addActor(windowCreditos);
    }

    /**
     * Ventana de controles a utilizar
     */
    private void controllerWindow() {
        windowControlHelp = new Window("", new Skin(Gdx.files.internal("ui/normal.json")));
        windowControlHelp.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        windowControlHelp.setMovable(false);
        windowControlHelp.setModal(true);
        windowControlHelp.setVisible(false);
        windowControlHelp.setLayoutEnabled(false);
        windowControlHelp.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("images/controles.png"))));

        closeControlHelp = new ImageButton(new Skin(Gdx.files.internal("ui/normal.json")));
        closeControlHelp.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/closeWindow.png")));
        closeControlHelp.getStyle().over = new TextureRegionDrawable(new TextureRegion(new Texture("ui/closeWindowHover.png")));
        closeControlHelp.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/closeWindowHover.png")));
        closeControlHelp.setPosition(windowControlHelp.getWidth() - closeControlHelp.getWidth() - 15, windowControlHelp.getHeight() - closeControlHelp.getHeight() - 15);
        windowControlHelp.add(closeControlHelp);
        stage.addActor(windowControlHelp);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
