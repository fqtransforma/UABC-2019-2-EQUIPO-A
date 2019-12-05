package com.mygame.pooa.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Clase responsable de crear los limites del escenario donde interactuan nuestros actores
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 */

public class Banda extends Entity {
    private Fixture fixture;

    /**
     * @param world Mundo contenedor
     * @param position La posicion de la entidad (relacion Scene2D)
     * @param size Tamaño del contenedor (rectangular)
     */
    public Banda(World world, Vector2 position, Vector2 size) {
        super(world, position.x, position.y, BodyDef.BodyType.StaticBody);

        ChainShape shape = new ChainShape();
        shape.createChain(new float[]{
                position.x, position.y,
                position.x + size.x, position.y,
                position.x + size.x, position.y + size.y,
//                position.x, position.y + size.y,
//                position.x, position.y

        });
        fixture = body.createFixture(shape, 5f);
        fixture.setFriction(0f);
        shape.dispose();
    }
}
