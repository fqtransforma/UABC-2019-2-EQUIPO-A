package com.mygame.pooa.actors.reciclar.categoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.pooa.actors.Player;
import com.mygame.pooa.actors.reciclar.Bote;
import com.mygame.pooa.manager.screen.Hub;
import com.mygame.pooa.utils.CreateJoin;

import java.util.Vector;

/**
 * Contendra a los objetos para la detencion de colisiones, renderizado de los objetos
 * @author Abraham Medina Carrillo
 * @author Jesus Emmanuel Rodriguez Estrada
 * @author Alejandro Gonzalez Zepeda
 * @see Objeto
 */

public class Objetos {
    private Vector<Objeto> objetos;
    private JointDef jointDef;
    private Joint joint;
    private boolean createJoin = false;

    private World world;
    public static boolean isOverlapPlayer = false;

    /**
     * @param world Mundo donde se colocaran los actores y demas entidades
     */

    public Objetos(World world) {
        this.world = world;
        objetos = new Vector<>();
    }

    /**
     * @param x Posicion en x
     * @param y Posicion en y
     * @param type Tipo de material (necesario para las colisiones en los botes)
     * @param texture Imagen que tendra el objeto a la hora de renderizarse
    */

    public void addObjeto(int x, int y, Texture texture, Bote.Type type) {
        objetos.add(new Objeto(world, x, y, BodyDef.BodyType.DynamicBody, texture, type));
    }

    /**
     * @param player Jugador principal, deteccion de colisiones con el mismo, si se encuentra en el rango, y la tecla indicada esta presionada, realizara la coneccion
     * @see CreateJoin
     * @param batch Necesario para dibujar los graficos de cada objeto
     * @param botes Necesaria para la detenccion de colision con los mismo, deben incluir el tipo del cual son.
     * @see Bote
     */

    public void render(Batch batch, Player player, Bote[] botes) {
        for(int k=0; k<objetos.size(); k++) {
            if(objetos.get(k).getPosition().x < -5f) {
                removeObjeto(objetos.get(k));
                Hub.objectDestroy--;
            }
        }

        for(int k=0; k<objetos.size(); k++) {
            objetos.get(k).render(batch);
            isOverlapPlayer = Intersector.overlapConvexPolygons((Polygon) player.getShape(), (Polygon) objetos.get(k).getShape());
            if(isOverlapPlayer) {
                if((Player.PressClose || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !createJoin) {
                    jointDef = CreateJoin.Rope(player.getBody(), objetos.get(k).getBody(), 6f);
                    joint = world.createJoint(jointDef);
                    createJoin = true;
                }
            }
            if( (!Player.PressClose && !Gdx.input.isKeyPressed(Input.Keys.SPACE) ) && createJoin) {
                world.destroyJoint(joint);
                createJoin = false;
            }
        }

        for(int j=0; j<botes.length; j++) {
            for(int k=0; k<objetos.size(); k++) {
                Rectangle temp = ((Polygon) botes[j].getShape()).getBoundingRectangle();
                final boolean isOverlapBotes = Intersector.overlaps( temp , ((Polygon) objetos.get(k).getShape()).getBoundingRectangle() );
                if(isOverlapBotes && (botes[j].getType() == objetos.get(k).getType())) {
                    if(createJoin) {
                        world.destroyJoint(joint);
                        createJoin = false;
                    }
                    removeObjeto( objetos.get(k) );
                    botes[j].incrementCount();
                }
            }
        }
    }

    /**
     * @param objeto Objeto que sera destruido del conjunto principal (arreglo) y del mundo donde se encuentra
     */

    public void removeObjeto(Objeto objeto) {
        objetos.remove(objeto);
        objeto.dispose(world);
    }

    public void dispose() {
        for(int k=0; k<objetos.size(); k++) removeObjeto(objetos.get(k));
    }
}
