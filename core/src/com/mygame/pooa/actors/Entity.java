package com.mygame.pooa.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import java.io.Serializable;

public abstract class Entity extends Actor implements Serializable, Disposable {
    protected World world;
    protected Body body;
    public Entity(World world, float x, float y, BodyDef.BodyType bodyType) {
        this.world = world;

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

    @Override
    public void dispose() {
        world.destroyBody(body);
    }
}
