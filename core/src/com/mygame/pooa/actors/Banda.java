package com.mygame.pooa.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Banda extends Entity {
    private Fixture fixture;

    public Banda(World world, Vector2 position, Vector2 size) {
        super(world, position.x + size.x / 2, position.y + size.y / 2, BodyDef.BodyType.KinematicBody);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2f, size.y / 2f);
        fixture = body.createFixture(shape, 15f);
        fixture.setFriction(0f);
        shape.dispose();
    }
}
