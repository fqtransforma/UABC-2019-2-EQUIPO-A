/**
 *  SEPARADOR 3000 1.0
 *  APLICACION PARA ESCRITORIO DE ENTRETENIMIENTO Y CONCIENTIZACION SOBRE EL RECICLAJE
 *  @author Abraham Medina Carrillo
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 *  CORREO ELECTRONICO {alejandro.gonzalez96, abraham.medina.carrillo, emmanuel.rodriguez40 }@uabc.edu.mx
 *  UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA
 *  http://uabc.mx
 */

package com.mygame.pooa.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygame.pooa.actors.Entity;

/**
 * Manejador del personaje principal
 */

public class Joystick {
    private Entity entity;
    private float velocity;

    /**
     * @param entity La entidad a la cual afectara el controlador
     * @param velocity La velocidad constante a la cual se movera ()
     */

    public Joystick(Entity entity, float velocity) {
        this.entity = entity;
        this.velocity = velocity;
    }

    /**
     * Actualizacion de los controles y la velocidad del personaje principal
     */

    public void render() {
        entity.getBody().setLinearVelocity(0, 0);
        if( Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x + velocity, entity.getBody().getLinearVelocity().y);
        if( Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x - velocity, entity.getBody().getLinearVelocity().y);
        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x, entity.getBody().getLinearVelocity().y + velocity);
        if( Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x, entity.getBody().getLinearVelocity().y - velocity);
    }
}
