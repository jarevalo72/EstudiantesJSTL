/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represnta un curso inscrito por el estudiante
 * @author Ing. Jorge A. Arévalo A.
 */
public class Curso {
    /** Identificador del curso */
    private int id;
    /** Nombre del curso */
    private String nombre;

    /**
     * Constructor por defecto para un curso.
     */
    public Curso() {
        this.id = 0;
        this.nombre = "";
    }
    
    /**
     * Constructor con parámetros para un curso.
     * @param id Identificador del curso.
     */
    public Curso(int id) {
        this.id = id;
        this.nombre = "";
    }

    /**
     * Permite conocer el identificador del curso.
     * @return Identificador del curso.
     */
    public int getId() {
        return id;
    }

    /**
     * Permite actualizar el identificador del curso
     * @param id Identificador del curso
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permite conocer el nombre del curso.
     * @return Nombre del curso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite actualiozar el nombre del curso.
     * @param nombre Nombre del curso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
