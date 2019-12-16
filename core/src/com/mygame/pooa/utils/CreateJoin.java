package com.mygame.pooa.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

/**
 * Creacion de Joins para interactuar en el mundo con fisicas proporcionada por Box2D
 */

public class CreateJoin {

    /**
     * Creara un elemento que unira a las entidades (a sus {@link Body}s), aun podra moverse de forma independiente, pero sin alejarse mas de la distancia designada
     * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
     * @author Jesus Emmanuel Rodriguez Estrada
     * @author Alejandro Gonzalez Zepeda
     * @param bodyA Cuerpo al cual se le unira el {@link Body}B
     * @param bodyB Seguira al {@link Body}A
     * @param maxLength Maxima longitud a la cual se podra alejar el cuerpo A y B
     * @return Retorna la relacion de los cuerpos principales (Join)
     */

    public static RopeJointDef Rope(Body bodyA, Body bodyB, float maxLength) {
        RopeJointDef jointDef = new RopeJointDef();
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.collideConnected = true;
        jointDef.maxLength = maxLength;
        return jointDef;
    }
}