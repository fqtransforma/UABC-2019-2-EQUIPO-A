package com.mygame.pooa.actors.basura;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.pooa.actors.Entity;

public class Objeto extends Entity {
    private Fixture fixture;
    private Texture texture;
    public Objeto(World world, float x, float y, BodyDef.BodyType bodyType, Texture texture) {
        super(world, x, y, bodyType);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(4 / 2f, 4 / 2f);
        fixture = body.createFixture(shape, 5f);
        fixture.setFriction(10f);
        fixture.setRestitution(0.1f);
        shape.dispose();
        body.setAngularVelocity(0.1f);

        this.texture = texture;
    }

    public void render(Batch batch) {
        Sprite sprite = new Sprite(texture);
        sprite.setSize(4f, 4f);
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.setOriginCenter();
        sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}
