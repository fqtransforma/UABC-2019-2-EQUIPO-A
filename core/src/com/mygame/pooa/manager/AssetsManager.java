/**
 *  SEPARADOR 3000 1.0
 *  APLICACION PARA ESCRITORIO DE ENTRETENIMIENTO Y CONCIENTIZACION SOBRE EL RECICLAJE
 *  AUTORES: Gonzalez Zepeda Alejandro, Medina Carrillo Abraham, Rodriguez Estrada Jesus Emmanuel
 *  CORREO ELECTRONICO {alejandro.gonzalez96, abraham.medina.carrillo, emmanuel.rodriguez40 }@uabc.edu.mx
 *  UNIVERSIDAD AUTONOMA DE BAJA CALIFORNIA
 *  http://uabc.mx
 */

package com.mygame.pooa.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygame.pooa.actors.reciclar.Bote;

import java.util.Random;

/**
 * Contiene los recursos necesarios para utilizar durante el juego
 *  @author Abraham Medina Carrillo
 *  @author Jesus Emmanuel Rodriguez Estrada
 *  @author Alejandro Gonzalez Zepeda
 */

public class AssetsManager extends AssetManager {
    public AssetsManager() {
        // Musica
        addAsset("sound1", Music.class, Gdx.audio.newMusic(Gdx.files.internal("sounds/music/BlackBetty.mp3")));
        // Imagenes
        addAsset("player", Texture.class, new Texture(Gdx.files.internal("images/badlogic.jpg")));
        addAsset("metal", Texture.class, new Texture(Gdx.files.internal("images/badlogic2.jpg")));
        addAsset("papel", Texture.class, new Texture(Gdx.files.internal("images/badlogic.jpg")));
        addAsset("organico", Texture.class, new Texture(Gdx.files.internal("images/badlogic3.jpg")));
    }

    /**
     * Busca en los tipos de basura y asigna de los existentes.
     * @param data Tipo de basura en texto
     * @return Tipo de basura para colisionar
     */

    public static Bote.Type getType(String data) {
        if(data.toLowerCase().equals("metal")) return Bote.Type.METAL;
        if(data.toLowerCase().equals("papel")) return Bote.Type.PAPEL;
        return Bote.Type.ORGANICO;
    }

    /**
     * Genera una cadena aleatoria basada en los tipos de material existentes
     * @return Cadena random de los tipos existente
     */
    public static String getRandom() {
        String material[] = {"metal","papel","organico"};
        return material[new Random().nextInt(material.length)];
    }
}
