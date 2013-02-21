/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.json;

import com.google.gson.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ausiasmarch.dao.PeliculaDao;
import net.ausiasmarch.pojo.GenericPojo;
import net.ausiasmarch.pojo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Custom Serialize/Unserialize Pelicula to Json
 *
 * @author María Galbis
 */
public class JsonData implements JsonSerializer, JsonDeserializer {

    public static String toJson(GenericPojo o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new JsonData()).create();
        return gson.toJson(o);
    }

    public static String toJson(List<? extends GenericPojo> o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new JsonData()).create();
        return gson.toJson(o);
    }

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject(); //creamos un objeto Json
        for (Field f : src.getClass().getDeclaredFields()) { //recorremos los campos del pojo



            try {
                //ponemos la primera letra en mayúscyla (para llamar a su método get)
                String s = Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
                Method m = src.getClass().getMethod("get" + s);  //llamamos a sus métodos get

                System.out.println(m.invoke(src)+" "+(m.invoke(src) instanceof Collection));
                if (m.invoke(src) instanceof GenericPojo) { //si el método devuelve un POJO
                    GenericPojo g = (GenericPojo) m.invoke(src);
                    JsonObject aux = new JsonObject(); //creamos un objecto Json auxiliar

                    for (Field f2 : g.getClass().getDeclaredFields()) { //recorremos los campos del pojo
                        String methodStr;
                        if (f2.getName().contains("get") && !f2.getName().contains("Comment") && !f2.getName().contains("Hibernate")) {
                            methodStr = f2.getName().substring(f2.getName().indexOf("g"), f2.getName().lastIndexOf("$"));
                            
                            Method m2 = g.getClass().getMethod(methodStr); //llamamos a sus métodos get
                            
                            if (!(m2.invoke(m.invoke(src)) instanceof Collection)) { //si no es una colección
                                //agregamos propiedad al pojo: nombre del campo, string de lo que devuelva el método
                                //System.out.println(m2.invoke(g).toString());
                                aux.addProperty(f2.getName(), m2.invoke(g).toString());
                            }
                            
                        }
                    }

                    //agregamos el objeto json auxiliar al objeto json principal
                    jsonObject.add(f.getName(), aux);

                } else if (m.invoke(src) instanceof Collection) {  //si el método devuelve una colección
                    
                    Collection col = (Collection) m.invoke(src);
                    System.out.println(col);
                    JsonArray intArr = new JsonArray();  //creamos un array Json

                    //recorremos cada uno de sus objetos pojo
                    for (GenericPojo g : (Collection<GenericPojo>) m.invoke(src)) {
                        JsonObject aux = new JsonObject(); //creamos un objeto pojo auxiliar

                        //recorremos los campos de cada objeto
                        for (Field f3 : g.getClass().getDeclaredFields()) {
                            String s3 = Character.toUpperCase(f3.getName().charAt(0)) + f3.getName().substring(1);
                            Method m3 = g.getClass().getMethod("get" + s3);

                            if (!(m3.invoke(g) instanceof Collection)) { //si no es una colección
                                aux.addProperty(f3.getName(), m3.invoke(g).toString());
                            }

                        }

                        intArr.add(aux); //agregamos el objeto json al array
                    }

                    //agregamos el array al objeto json principal
                    jsonObject.add(f.getName(), intArr);


                } else { // si no es ninguno de los otros

                    jsonObject.addProperty(f.getName(), m.invoke(src).toString());

                }

            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(JsonData.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

        return jsonObject;
    }

    @Override
    public GenericPojo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}