package com.mygame.pooa.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.actors.Banda;
import com.mygame.pooa.actors.Player.Player;
import com.mygame.pooa.actors.reciclar.categoria.Objetos;
import com.mygame.pooa.actors.reciclar.Bote;
import com.mygame.pooa.manager.AssetsManager;
import com.mygame.pooa.screens.other.GameMenu;
import com.mygame.pooa.screens.other.GameOver;
import com.mygame.pooa.screens.other.Hub;

/**
 * Contiene los objetos relacionados a la pantalla del juego, incluye el estado y ciclo de vida del mismo
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 * @see com.badlogic.gdx.Screen
 * @see com.mygame.pooa.screens.GameScreen
 */

public class PlayScreen extends GameScreen {
    public static final float PPM = 32f; // Relacion de Box2D y Scene2D

    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Stage stage;
    private Hub hub;
    private GameOver gameOver;
    private GameMenu gameMenu;

    private Player player;
    private Objetos objetos;
    private Banda banda;
    private Bote[] botes;

    private static float GravedadX = -2f;
    private static float timeSleep = 0;
    private static float time = 0;
    private static boolean inicializado = false;
    public static boolean isPause = false;
    public static float soundVolume = 1;

    private Music firstSound;
    private static MyGamePOOA game;
    private Image image;

    public PlayScreen(MyGamePOOA game) {
        super(game);
        PlayScreen.game = game;
    }

    @Override
    public void show() {
        image = new Image(new Texture("images/backgroundMain.jpg"));
        image.setScale(1 / PPM * 2);

        isPause = false;
        resetTimeSleep();
        firstSound = game.assetsManager.get("sound1");
        firstSound.setLooping(true);
        firstSound.play();
        firstSound.setVolume(soundVolume);

        inicializado = true;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / (PPM / 2), Gdx.graphics.getHeight() / (PPM / 2));

        world = new World(new Vector2(GravedadX * PPM / 5, -9.8f * PPM / 5), false);
        b2dr = new Box2DDebugRenderer();
        b2dr.setDrawJoints(false);
        b2dr.setDrawBodies(false);

        stage = new Stage(new ScreenViewport());
        Hub.objectDestroy = 5;
        hub = new Hub(stage);
        gameOver = new GameOver(hub.getSkin(), game);
        gameMenu = new GameMenu(hub.getSkin(), game);

        player = new Player(world, new Vector2(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM), new Vector2(4f, 4f));
        banda = new Banda(world, new Vector2(-20, 0.1f), new Vector2(Gdx.graphics.getWidth() / PPM * 4 + 60, Gdx.graphics.getHeight()));
        objetos = new Objetos(world);

        botes = new Bote[3];
        final float positionBotes = Gdx.graphics.getWidth() / 10f;
        botes[0] = new Bote(new Vector2(positionBotes, Gdx.graphics.getHeight() / 10f * 6.5f), new Vector2(positionBotes * 2, 5 * PPM), Bote.Type.PAPEL, stage, hub.getSkin());
        botes[1] = new Bote(new Vector2(positionBotes * 4, Gdx.graphics.getHeight() / 10f * 6.5f), new Vector2(positionBotes * 2, 5 * PPM), Bote.Type.METAL, stage, hub.getSkin());
        botes[2] = new Bote(new Vector2(positionBotes * 7, Gdx.graphics.getHeight() / 10f * 6.5f), new Vector2(positionBotes * 2, 5 * PPM), Bote.Type.ORGANICO, stage, hub.getSkin());

        stage.addActor(banda);
    }

    /**
     * Actualizacion de tiempo, aparicion de objetos nuevos y renderizado de las fisicas y movimiento de los mismos.
     * @param delta intervalo de tiempo entre frame y frame
     */
    private void update(float delta) {
        if(time>=timeSleep) {
            time = 0;
            if(timeSleep > 0.5) timeSleep-=0.08f;

            final String temp = AssetsManager.getRandom();
            objetos.addObjeto((int) (Gdx.graphics.getWidth() / PPM * 2.2), (int) (Gdx.graphics.getHeight() / PPM) * 2, getAssetManager().get(temp), AssetsManager.getType(temp));
        }
        hub.update();
        time += delta;
        game.getBatch().setProjectionMatrix(camera.combined);
        world.step(1 / 60f, 6, 2);

        if(!firstSound.isPlaying()) firstSound.play();
        if(Hub.objectDestroy <= 0) PlayScreen.isPause = true;
    }

    /**
     * Funcion solo de monitoreo ** eliminar en produccion **
     */
    private void eventHandler() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) isPause = !isPause;
        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) b2dr.setDrawBodies(!b2dr.isDrawBodies());
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) Gdx.app.exit();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        image.draw(game.getBatch(), 1);
        game.getBatch().end();
        eventHandler();

        botes[0].render(game.getBatch());
        botes[1].render(game.getBatch());
        botes[2].render(game.getBatch());

        objetos.render(game.getBatch(), player, botes);
        player.update(game.getBatch());

        stage.act();
        b2dr.render(world, camera.combined);
        stage.draw();

        if(!isPause) {
            update(delta);
            hub.update();
        } else {
            if(Hub.objectDestroy <= 0) gameOver.render();
            else gameMenu.render();
        }

        firstSound.setVolume(PlayScreen.soundVolume);
    }

    /**
     * @return Recursos a utilizar
     */
    public static AssetsManager getAssetManager() {
        return game.assetsManager;
    }

    @Override
    public void hide() {
        firstSound.stop();
    }

    /**
     * Reseteo de valores predeterminados, velocidad de aparicion de objetos y puntaje del jugador
     */
    public static void resetTimeSleep() {
        timeSleep = 5;
        time = 0;
        Player.Points = 0;
    }

    @Override
    public void dispose() {
        if(inicializado) {
            banda.dispose(world);
            player.dispose(world);
            objetos.dispose();
            stage.dispose();
            b2dr.dispose();
            world.dispose();
        }
    }
}
