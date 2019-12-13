package com.mygame.pooa.actors.reciclar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygame.pooa.actors.Player.Player;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Clase necesaria para la obtencion de puntos al realizar una clasificacion correcta de materiales, los cuales deben ser del mismo tipo que el bote
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */

public class Bote {

    /**
     * Tipos de material que el bote puede adoptar
     */

    public enum Type {
        PAPEL,
        METAL,
        ORGANICO
    }

    private static Texture setTexture(Type type) {
        switch (type) { // Por defecto sera organico
            case METAL: return PlayScreen.getAssetManager().get("metal");
            case PAPEL: return PlayScreen.getAssetManager().get("papel");
            default: return PlayScreen.getAssetManager().get("organico");
        }
    }

    private Texture texture;
    private Polygon polygon;
    private Type type;

    private Vector2 position;
    private Vector2 size;
    private Label label;
    private int countObject = 0;

    /**
     * @param stage Escenario en el cual se agregaran los componentes de UI y Actores
     * @param size Dimencion del bote
     * @param position Posicion del bote
     * @param type Tipo de material con el cual colisionara el bote
     * @param skin Estilo grafico que tendran los componentes de UI
     * @see Skin
     * @see Stage
     * @see com.badlogic.gdx.scenes.scene2d.Actor
    */

    public Bote(Vector2 position, Vector2 size, Type type, Stage stage, Skin skin) {
        this.position = position;
        this.size = size;
        final float[] vector = {
                0, 0,
                size.x, 0,
                size.x, size.y,
                0, size.y,
                0, 0
        };
        texture = setTexture(type);
        polygon = new Polygon(vector);
        polygon.setPosition(position.x, position.y);
        this.type = type;

        label = new Label("" + countObject, skin);
        label.setPosition(position.x + size.x / 2f - label.getWidth() / 2f, position.y + size.y + label.getHeight() / 2f);
        stage.addActor(label);
    }
    public Shape2D getShape() {
        return polygon;
    }

    public Type getType() {
        return type;
    }

    public void incrementCount() {
        label.setText("" + ++countObject);

        if(type == Type.PAPEL) Player.Points += 5;
        if(type == Type.METAL) Player.Points += 15;
        if(type == Type.ORGANICO) Player.Points += 25;
    }

    /**
     * @param batch Necesario para dibujar los graficos
     */

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, position.x / PlayScreen.PPM * 2, position.y / PlayScreen.PPM * 2, size.x / PlayScreen.PPM * 2, size.y / PlayScreen.PPM * 2);
        batch.end();
    }
}
