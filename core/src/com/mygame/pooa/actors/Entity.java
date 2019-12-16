package com.mygame.pooa.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Contiene la configuracion necesaria que tendra cada uno de nuestros objetos que seran afectados por las fisicas de nuestro mundo
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
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

    /**
     * @return La posicion del cuerpo afectado por las fisicas
     */
    public Vector2 getPosition() {
        return body.getPosition();
    }

    /**
     * @return La posicion el cuerpo afectado por las fisicas
     */
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
