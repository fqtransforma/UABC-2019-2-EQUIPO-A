/**
 *  SEPARADOR 3000 1.0
 *  @author Abraham Medina Carrillo
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 *  CORREO ELECTRONICO {alejandro.gonzalez96, abraham.medina.carrillo, emmanuel.rodriguez40 }@uabc.edu.mx
 *  UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA
 *  http://uabc.mx
 *
 */
package com.mygame.pooa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.mygame.pooa.controller.Joystick;
import com.mygame.pooa.screens.PlayScreen;
import com.mygame.pooa.utils.CreateJoin;
import com.mygame.pooa.utils.CreateShape;

/**
 * @see Joystick
 * @see Box2D
 * @see Entity
 *
 * Jugador principal, incluye fisicas personalizadas y variables necesarias para su colision con las diferentes entidades
 */

public class Player extends Entity {
    public static boolean PressClose = false;
    public static int Points = 0;

    private Texture texture;
    private Fixture fixture;
    private Joystick joystick;

    private Rectangle rectangle;
    private Polygon polygon;
    private Sprite sprite;

    private static float velocity = 50f;

    public Player(World world, Vector2 position, Vector2 size) {
        super(world, position.x, position.y, BodyDef.BodyType.DynamicBody);
        this.texture = PlayScreen.getAssetManager().get("player");
        body.setTransform(body.getPosition(), (float) Math.toRadians(-90f));
        joystick = new Joystick(this, velocity);
        sprite = new Sprite(texture);

        rectangle = new Rectangle(position.x, position.y, 2f * PlayScreen.PPM + 4, 2f * PlayScreen.PPM + 4);
        final float vertices[] = {0, 0, rectangle.getWidth(), 0, rectangle.getWidth(), rectangle.getHeight(), 0, rectangle.getWidth()};
        polygon = new Polygon(vertices);
        polygon.setOrigin(rectangle.getWidth()/2, rectangle.getHeight()/2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2f, size.y / 2f);
        fixture = body.createFixture(shape, 30f);
        fixture.setFriction(10f);
        fixture.setRestitution(0f);
        shape.dispose();

        body.setFixedRotation(true);
        body.setLinearDamping(0f);
        body.setGravityScale(0f);
        body.setAngularVelocity(0f);

        // ********************************
        // CONTIENE EL ELEMENTO ESTATICO AL CUAL ESTARA LIGADO "NUESTRA GARRA"
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(Gdx.graphics.getWidth() / PlayScreen.PPM, 45);
        bodyDef2.type = BodyDef.BodyType.KinematicBody;
        bodyDef2.angle = (float) Math.toRadians(-90f);
        Body body2 = world.createBody(bodyDef2);

        // Linea de estiramiento maximo
        CreateShape.Polygon(size.x/2f, size.y/2f, body2, 15f, 10f);
        RopeJointDef jointDef = CreateJoin.Rope(body, body2, 43f);
        world.createJoint(jointDef);
    }

    /**
     * Actualiza la posicion del sprite a dibujar, tomando la posicion del Body
     * @see Body
     * @see Entity
     * @param batch Necesario para dibujar el Sprite de la entidad
     */

    public void update(Batch batch) {
        joystick.render();
        sprite.setSize(4f, 4f);
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.setOriginCenter();
        sprite.setRotation((float) Math.toDegrees(body.getAngle()) + 90f);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        polygon.setPosition(body.getPosition().x * PlayScreen.PPM / 2 - rectangle.width / 2, body.getPosition().y * PlayScreen.PPM / 2 - rectangle.height / 2);
        polygon.setRotation(sprite.getRotation());
    }

    /**
     * Se utilizara en la implementacion del Joystick
     * @param x Posicion recorrida por el Joystick en X
     * @param y Posicion recorrida por el Joystick en Y
     */

    public void movePlayer(float x, float y) {
        body.setLinearVelocity(body.getLinearVelocity().x + (velocity * x), body.getLinearVelocity().y + (velocity * y));
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Shape2D getShape() {
        return polygon;
    }
}
