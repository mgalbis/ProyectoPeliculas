/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ausiasmarch.dao.GenericDao;
import net.ausiasmarch.dao.PeliculaDao;
import net.ausiasmarch.dao.PeliculaDaoInterface;
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Pelicula;
import net.ausiasmarch.pojo.Usuario;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author María Galbis
 */
@Controller
public class PeliculaController {
    
 
    
    @RequestMapping({"/index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //autenticación
        //Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Pelicula p = (Pelicula) sesion.get(Pelicula.class, 4);
        //System.out.println(p.getTitulo());
        
        GenericDao d = new PeliculaDao();
        //Pelicula p = new Pelicula();
        //p.setId(4);
        //System.out.println(((Pelicula) d.read(p)).getTitulo());
        
        List<Pelicula> lista = d.readAll();
        System.out.println(lista);
        
        return new ModelAndView("index");
    }
    
    @RequestMapping({"/peliculas.json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        return new ModelAndView();
    }
    
    @RequestMapping({"pelicula.json"})
    public ModelAndView pelicula(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        return new ModelAndView();
    }
    
    @RequestMapping({"actores.json"})
    public ModelAndView actores(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        return new ModelAndView();
    }
    
    @RequestMapping({"actor.json"})
    public ModelAndView actor(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        return new ModelAndView();
    }
    
}
