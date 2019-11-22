package com.mygame.pooa.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.mygame.pooa.controller.Joystick;
import com.mygame.pooa.utils.CreateJoin;
import com.mygame.pooa.utils.CreateShape;

public class Player extends Entity {
    private Fixture fixture;

    private Body left, right;
    private Joystick joystick;

    public Player(World world, Vector2 position, Vector2 size) {
        super(world, position.x + (size.x / 2f), position.y + (size.y / 2f), BodyDef.BodyType.DynamicBody);
        body.setTransform(body.getPosition(), (float) Math.toRadians(-90f));
        joystick = new Joystick(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2f, size.y / 2f);
        fixture = body.createFixture(shape, 15f);
        fixture.setFriction(10f);
        fixture.setDensity(30);
        fixture.setRestitution(0f);
        shape.dispose();

        body.setFixedRotation(true);
        body.setLinearDamping(0f);
        body.setGravityScale(0f);
        body.setAngularVelocity(0f);

//        ********************************
//        CONTIENE EL ELEMENTO ESTATICO AL CUAL ESTARA LIGADO "NUESTRA GARRA"
//        INCLUYE UN ESTIRAMIENTO MAXIMO DE HASTA 45 UNIDADES
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(40, 45);
        bodyDef2.type = BodyDef.BodyType.KinematicBody;
        bodyDef2.angle = (float) Math.toRadians(-90f);
        Body body2 = world.createBody(bodyDef2);


        CreateShape.Polygon(size.x/2f, size.y/2f, body2, 15f, 10f);
        RopeJointDef jointDef = CreateJoin.Rope(body, body2, 40f);
        world.createJoint(jointDef);

//        ********************************
        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.position.set(body.getPosition().x + 15, body.getPosition().y);
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.angle = (float) Math.toRadians(-90f);
        left = world.createBody(bodyDef3);


        CreateShape.Polygon(size.x/7f*6, size.y/6f, left, 50f, 10f);
        RevoluteJointDef revoluteJointDef = CreateJoin.Revolution(body, new Vector2(3, size.y), left, new Vector2(-4, 0), -45f, 0f);
        world.createJoint(revoluteJointDef);

//        ********************************
        BodyDef bodyDef4 = new BodyDef();
        bodyDef4.position.set(body.getPosition().x + 15, body.getPosition().y);
        bodyDef4.type = BodyDef.BodyType.DynamicBody;
        bodyDef4.angle = (float) Math.toRadians(0f);
        right = world.createBody(bodyDef4);
        right.setAngularVelocity(1f);


        CreateShape.Polygon(size.x/7f*6, size.y/6, right, 50f, 10f);
        RevoluteJointDef revoluteJointDef2 = CreateJoin.Revolution(body, new Vector2(3, -size.y), right, new Vector2(-4, 0), 0f, 45f);
        revoluteJointDef2.enableMotor = true;
        world.createJoint(revoluteJointDef2);
    }

    public void updateControll() {
        if( Gdx.input.isKeyPressed(Input.Keys.SPACE) )  {
            left.setAngularVelocity(-40f);
            right.setAngularVelocity(40f);
        } else {
            left.setFixedRotation(false);
            right.setFixedRotation(false);
            left.setAngularVelocity(40f);
            right.setAngularVelocity(-40f);
        }
        joystick.render();
    }

    public void update(Batch batch) {
        updateControll();
    }
}
