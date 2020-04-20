/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un estudiante
 * @author Jorge A
 */
public class Estudiante {
    /** Identificador del estudiante */
    private int id;
    /** Nombre del estudiante */
    private String nombre;
    /** Apellido del estudiante */
    private String apellido;
    /** Email del estudiante */
    private String email;
    /** Toto del estudiante */
    private String foto;
    /** Lista de cursos del estudiante */
    private ArrayList<Curso> cursos;

    /**
     * Constructor por defector para crear un estudiante
     */
    public Estudiante() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.email = "";
        this.foto = "";
        this.cursos = new ArrayList<Curso>();
    }

    /**
     * Permite concer el Identificador del estudiante.
     * @return Identificador del estudiante.
     */
    public int getId() {
        return id;
    }

    /**
     * Permite actualizar el Identificador del estudiante.
     * @param id Identificador del estudiante.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permite conocer el Nombre del estudiante.
     * @return Nombre dek estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite actualizar el Nombre del estudiante.
     * @param nombre Nombre dek estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite conocer el Apellido del estudiante.
     * @return Apellido dek estudiante.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Permite actualizar el Apellido del estudiante.
     * @param apellido Apellido dek estudiante.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Permite conocer el Email del estudiante.
     * @return Email del estudiante.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permite actualizar el Email del estudiante.
     * @param email Email del estudiante.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Permite conocer la Foto del estudiante.
     * @return Foto del estudiante.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Permite actualizar la Foto del estudiante.
     * @param foto Foto del estudiante.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Permite conocer la Lista de cursos del estudiante.
     * @return Lista de cursos del estudiante.
     */
    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    /**
     * Permite actualizar la Lista de cursos del estudiante.
     * @param cursos Lista de cursos del estudiante.
     */
    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
    /**
     * Permite obtener la Lista de cursos del estudiante.
     * @return Lista de cursos del estudiante.
     */
    public List<Curso> getCursosEstudiante() {
        List<Curso> cursos = new ArrayList<Curso>();
        try {
            GestionDatos gd = new GestionDatos();
            cursos = gd.listarCursosEstudiante(this.id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }

}
