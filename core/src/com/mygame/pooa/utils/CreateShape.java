package com.mygame.pooa.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Se manejan la formas de los objetos para su interacción
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 * @see Fixture
 * @see com.badlogic.gdx.physics.box2d.FixtureDef
 */

public class CreateShape {

    /**
     * Crea Poligono por defecto introducciodo solo las variables del constructor
     * @param width Ancho del poligono
     * @param height Alto del poligono
     * @param body Body contenedor: {@link com.mygame.pooa.actors.Entity}
     * @param densidad La relacion que tendra con los demas Entidades (¿podra atravesarlo?), le dara mas masa al objeto
     * @param friccion Friccion en relacion con los demas cuerpos del {@link com.badlogic.gdx.physics.box2d.World}
     * @return Retorna el poligono asignando al {@link Body} las variables asignadas
     */

    public static PolygonShape Polygon(float width, float height, Body body, float densidad, float friccion) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        Fixture fixture = body.createFixture(shape, densidad);
        fixture.setFriction(friccion);
        shape.dispose();
        return shape;
    }
}
