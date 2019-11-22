package com.mygame.pooa.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

public class CreateJoin {
    public static RopeJointDef Rope(Body bodyA, Body bodyB, float maxLength) {
        RopeJointDef jointDef = new RopeJointDef();
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.collideConnected = true;
        jointDef.maxLength = maxLength;
        return jointDef;
    }

    public static RevoluteJointDef Revolution(Body bodyA, Vector2 localAnchorA, Body bodyB, Vector2 localAnchorB, float lowerAngle, float upperAngle) {
        RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
        revoluteJointDef.bodyA = bodyA;
        revoluteJointDef.bodyB = bodyB;
        revoluteJointDef.localAnchorA.set(localAnchorA);
        revoluteJointDef.localAnchorB.set(localAnchorB);
        revoluteJointDef.enableLimit = true;
        revoluteJointDef.referenceAngle = 0;
        revoluteJointDef.lowerAngle = lowerAngle;
        revoluteJointDef.upperAngle = upperAngle;
        return revoluteJointDef;
    }
}