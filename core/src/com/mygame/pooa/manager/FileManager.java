package com.mygame.pooa.manager;

import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.Vector;

/**
 * Manejador de archivos, escritura y lectura mediante serializacion
 * Incluye la clase a serializar
 * @author <a href="https://github.com/medina1402" target="_blank">Abraham Medina Carrillo</a>
 * @see Vector
 * @see Scores
 * @see Serializable
 */

public class FileManager {
    /**
     * Implementacion escencial de serializacion para almacenar el score
     */
    public static class Scores extends Vector<Integer> implements Serializable {

        /**
         * Se inserta un elemento de manera ordenada.
         * @param element Elemento a insertar.
         * @return Posicion en la que se inserto el elemento.
         */
        public int addItem(Integer element) {
            int k=0;
            for(; k<elementCount; k++) {
                if(get(k) <= element) {
                    insertElementAt(element, k);
                    return k + 1;
                }
            }
            insertElementAt(element, k);
            return k + 1;
        }
    }

    /**
     * Escritura de archivo mediante serializacion
     * @param path direccion relativa de archivo
     * @return Vector con el contenido de la clase
     */
    public static Scores readFile(String path) {
        if(!Gdx.files.internal(path).exists()) return new Scores();

        Scores temp = new Scores();
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(Gdx.files.internal(path).path());
            objectInputStream = new ObjectInputStream(fileInputStream);

            temp = (Scores) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Almacena la clase completa mediante serializacion
     * @param vector Objecto a almacenar (clase)
     * @param path ruta del archivo
     */
    public static void saveFile(Scores vector, String path) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(Gdx.files.internal(path).path());
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(vector);
            assert bufferedOutputStream != null;
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert objectOutputStream != null;
            objectOutputStream.close();
            assert bufferedOutputStream != null;
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
