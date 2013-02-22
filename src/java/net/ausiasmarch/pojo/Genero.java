package net.ausiasmarch.pojo;
// Generated 14-feb-2013 15:47:47 by Hibernate Tools 3.2.1.GA


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Genero generated by hbm2java
 */
public class Genero  implements java.io.Serializable, GenericPojo {


     private Integer id;
     private String nombre;
     private Set peliculas = new HashSet(0);

    public Genero() {
    }

	
    public Genero(String nombre) {
        this.nombre = nombre;
    }
    public Genero(String nombre, Set peliculas) {
       this.nombre = nombre;
       this.peliculas = peliculas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getPeliculas() {
        return this.peliculas;
    }
    
    public void setPeliculas(Set peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String getComment(String column) {
        HashMap h = new HashMap();
        h.put("id", "Id");
        h.put("nombre", "Nombre");
        h.put("peliculas", "Películas");

        return (String) h.get(column);
    }




}


