package com.mygame.pooa.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygame.pooa.actors.Entity;

import java.io.Serializable;

public class Joystick implements Serializable {
    private Entity entity;
    public Joystick(Entity entity) {
        this.entity = entity;
    }

    public void render() {
        entity.getBody().setLinearVelocity(0, 0);
        if( Gdx.input.isKeyPressed(Input.Keys.D) ) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x + 50f, entity.getBody().getLinearVelocity().y);
        if( Gdx.input.isKeyPressed(Input.Keys.A) ) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x - 50f, entity.getBody().getLinearVelocity().y);
        if( Gdx.input.isKeyPressed(Input.Keys.W) ) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x, entity.getBody().getLinearVelocity().y + 50f);
        if( Gdx.input.isKeyPressed(Input.Keys.S) ) entity.getBody().setLinearVelocity(entity.getBody().getLinearVelocity().x, entity.getBody().getLinearVelocity().y - 50f);
    }
}
