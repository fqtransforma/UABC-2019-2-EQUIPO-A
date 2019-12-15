package com.mygame.pooa.actors.reciclar.categoria;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygame.pooa.actors.Entity;
import com.mygame.pooa.actors.reciclar.Bote;
import com.mygame.pooa.screens.PlayScreen;

/**
 * Encargada de crear los objetos a reciclar, dependera de la cantidad de tipos de Bote
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 * @see com.mygame.pooa.actors.Entity
 */

public class Objeto extends Entity {
    private Fixture fixture;
    public Texture texture;
    private Rectangle rectangle;

    private Bote.Type type;
    private Polygon polygon;

    private Sprite sprite;

    public Objeto(World world, float x, float y, BodyDef.BodyType bodyType, Texture texture, Bote.Type type) {
        super(world, x, y, bodyType);
        this.texture = texture;
        this.type = type;
        sprite = new Sprite(texture);

        rectangle = new Rectangle(x, y, 2f * PlayScreen.PPM + 4, 2f * PlayScreen.PPM + 4);
        final float vertices[] = {0, 0, rectangle.getWidth(), 0, rectangle.getWidth(), rectangle.getHeight(), 0, rectangle.getWidth()};
        polygon = new Polygon(vertices);
        polygon.setOrigin(rectangle.getWidth()/2, rectangle.getHeight()/2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(4 / 2f, 4 / 2f);
        fixture = body.createFixture(shape, 80f);
        fixture.setRestitution(0f);
        shape.dispose();
        body.setAngularVelocity(0.1f);
    }

    public Bote.Type getType() {
        return type;
    }

    /**
     * @param type Seleccion del tipo de material que tendra el objeto, afecta en los puntos que obtendra
     */
    public void SetType(Bote.Type type) {
        this.type = type;
    }

    /**
     * @param batch Necesario para dibujar el sprite modificado del actor
     */
    public void render(Batch batch) {
        sprite.setSize(4f, 4f);
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.setOriginCenter();
        sprite.setRotation((float) Math.toDegrees(body.getAngle()));

        batch.begin();
        sprite.draw(batch);
        batch.end();

        polygon.setPosition(body.getPosition().x * PlayScreen.PPM / 2 - rectangle.width / 2, body.getPosition().y * PlayScreen.PPM / 2 - rectangle.height / 2);
        polygon.setRotation(sprite.getRotation());
    }

    public Shape2D getShape() {
        return polygon;
    }
}
