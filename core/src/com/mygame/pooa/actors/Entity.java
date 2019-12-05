package com.mygame.pooa.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Contiene la configuracion necesaria que tendra cada uno de nuestros objetos que seran afectados por las fisicas de nuestro mundo
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 * @see Actor
 * @see Body
 */

public abstract class Entity extends Actor {
    protected Body body;
    public Entity(World world, float x, float y, BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = bodyType;
        body = world.createBody(bodyDef);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    /**
     * @param world Mundo contenedor, se destruira la entidad de este
     */

    public void dispose(World world) {
        world.destroyBody(body);
    }
}
