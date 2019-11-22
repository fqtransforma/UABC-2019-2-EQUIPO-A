package com.mygame.pooa.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygame.pooa.MyGamePOOA;
import com.mygame.pooa.actors.Banda;
import com.mygame.pooa.actors.Player;
import com.mygame.pooa.actors.basura.Objetos;

public class Home extends GameScreen {
    private OrthographicCamera camera;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Stage stage;
    private Player player;
    private Objetos objetos;
    private Banda banda;

    private static float time = 0;

    public Home(MyGamePOOA game) {
        super(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
//        camera.setToOrtho(false, Gdx.graphics.getWidth() / (PPM / 2), Gdx.graphics.getHeight() / (PPM / 2));
        camera.setToOrtho(false, Gdx.graphics.getWidth() / (PPM / 2), Gdx.graphics.getHeight() / (PPM / 2));

        world = new World(new Vector2(-4f * PPM / 5, -9.8f * PPM / 5), false);
        b2dr = new Box2DDebugRenderer();

        stage = new Stage();
        player = new Player(world, new Vector2(38, 35), new Vector2(4f, 4f));
        banda = new Banda(world, new Vector2(0, 0), new Vector2(120, 2));
        objetos = new Objetos(world);

        stage.addActor(banda);
        stage.addActor(player);
    }

    private void update(float delta) {
        if(time>=2) {
            time = 0;
            objetos.addObjeto(80, 15, new Texture("./badlogic.jpg"));
        }
        time += delta;
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(camera.combined);
        world.step(1 / 60f, 6, 2);
        objetos.render(game.getBatch());
        player.update(game.getBatch());

        stage.act();
        b2dr.render(world, camera.combined);
        stage.draw();
    }
}
