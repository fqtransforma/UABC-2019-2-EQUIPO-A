package com.mygame.pooa.actors.basura;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Vector;

public class Objetos {
    private Vector<Objeto> objetos;
    private World world;

    public Objetos(World world) {
        this.world = world;
        objetos = new Vector<>();
    }

    public void addObjeto(int x, int y, Texture texture) {
        objetos.add(new Objeto(world, x, y, BodyDef.BodyType.DynamicBody, texture));
    }

    public void render(Batch batch) {
        for(Objeto basura: objetos) basura.render(batch);
    }

    public void removeObjeto(Objeto objeto) {
        objetos.remove(objeto);
        objeto.dispose();
    }

    public void removeObjeto(int index) {
        Objeto temp = objetos.get(index);
        objetos.remove(temp);
        temp.dispose();
    }
}
