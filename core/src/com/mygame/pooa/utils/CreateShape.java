package com.mygame.pooa.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class CreateShape {
    public static PolygonShape Polygon(float width, float height, Body body, float densidad, float friccion) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        Fixture fixture = body.createFixture(shape, densidad);
        fixture.setFriction(friccion);
        shape.dispose();
        return shape;
    }
}
