/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite gestionar las transacciones con la base de datos.
 * @author Ing. Jorge A. Arévalo A.
 */
public class GestionDatos {
    /** Conexión lógica con la base de datos */
    private Conexion con;
    /** Registro de errores en las trasacciones */
    private String error;

    /**
     * Constructor por de defecto del gestor de transacciones.
     */
    public GestionDatos() {
        this.con = new Conexion();
    }

    /**
     * Permite obtener una instancia de la conexión a la base de datos.
     * @return con Conexión actual
     */
    public Conexion getCon() {
        return con;
    }

    /**
     * Permite obtener la losta de estudiantes de la base de datos.
     * @return Lista de estudiantes registrados.
     */
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();
        try {
            Connection c = con.conectar();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from estudiante");
            while(rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("estudiante_id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setEmail(rs.getString("email"));
                e.setFoto(rs.getString("foto"));
                estudiantes.add(e);
            }
            rs.close();
            for (Estudiante est : estudiantes) {
                ResultSet rst = stm.executeQuery("select curso.curso_id, curso.nombre_curso "
                        + "from estudiante_curso, curso "
                        + "where estudiante_curso.estudiante_id = " + est.getId()
                        + " and estudiante_curso.curso_id = curso.curso_id");
                while(rst.next()) {
                    Curso cur = new Curso();
                    cur.setId(rst.getInt("curso_id"));
                    cur.setNombre(rst.getString("nombre_curso"));
                    est.getCursos().add(cur);
                }
                rst.close();
            }
            stm.close();
            c.close();
        }
        catch(Exception e) {
            this.error = e.getMessage();
        }
        return estudiantes;
    }

    /**
     * Obtiene la losta de cursos registrados por un estudiante.
     * @param id Identificador del estudiante.
     * @return Lista de cursos registrados por un estudiante.
     */
    public List<Curso> listarCursosEstudiante(int id) {
        List<Curso> cursos = new ArrayList<Curso>();
        try {
            Connection c = con.conectar();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select curso.curso_id, curso.nombre_curso "
                    + "from estudiante_curso, curso "
                    + "where estudiante_curso.estudiante_id = " + id
                    + " and estudiante_curso.curso_id = curso.curso_id");
            while(rs.next()) {
                Curso cur = new Curso();
                cur.setId(rs.getInt("curso_id"));
                cur.setNombre(rs.getString("nombre_curso"));
                cursos.add(cur);
            }
            rs.close();
            stm.close();
            c.close();
        }
        catch(Exception e) {
            this.error = e.getMessage();
        }
        return cursos;
    }

    /**
     * Obtiene la lista de cursos registrados en la base de datos.
     * @return Lista de cursos registrados.
     */
    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<Curso>();
        try {
            Connection c = con.conectar();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from curso");
            while(rs.next()) {
                Curso cur = new Curso();
                cur.setId(rs.getInt("curso_id"));
                cur.setNombre(rs.getString("nombre_curso"));
                cursos.add(cur);
            }
            rs.close();
            stm.close();
            c.close();
        }
        catch(Exception e) {
            this.error = e.getMessage();
        }
        return cursos;
    }
    
    /**
     * Obtiene un estudiante de la base de datos.
     * @param id Identificador del estudiante.
     * @return Estudiante correspondiente al identificador.
     */
    public Estudiante getEstudianteId(int id) {
        Estudiante estudiante = new Estudiante();
        try {
            Connection c = con.conectar();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery("select * from estudiante where estudiante_id = " + id);
            if(rs.next()) {
                estudiante.setId(rs.getInt("estudiante_id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setEmail(rs.getString("email"));
                estudiante.setFoto(rs.getString("foto"));
            }
            rs.close();
            ResultSet rst = stm.executeQuery("select curso.curso_id, curso.nombre_curso "
                    + "from estudiante_curso, curso "
                    + "where estudiante_curso.estudiante_id = " + estudiante.getId()
                    + " and estudiante_curso.curso_id = curso.curso_id");
            while(rst.next()) {
                Curso cur = new Curso();
                cur.setId(rst.getInt("curso_id"));
                cur.setNombre(rst.getString("nombre_curso"));
                estudiante.getCursos().add(cur);
            }
            rst.close();
            stm.close();
            c.close();
        }
        catch(Exception e) {
            this.error = e.getMessage();
        }
        return estudiante;
    }
    
    /**
     * Actualiza los registros de un estudiante.
     * @param estudiante Estudiante a actualizar.
     * @return Estado de la trasacción, true si la transacción finalizó de forma
     *         correcta, false en caso contrario.
     */
    public boolean actualizarEstudiante(Estudiante estudiante) {
        boolean act = false;
        try {
            Connection c = con.conectar();
            Statement stm = c.createStatement();
            int res;
            res = stm.executeUpdate("delete from estudiante_curso where estudiante_id = "
                    + estudiante.getId());
            res = stm.executeUpdate("delete from estudiante where estudiante_id = "
                    + estudiante.getId());
            res = stm.executeUpdate("insert into estudiante values (" + 
                    estudiante.getId() + ", '" + estudiante.getNombre() + "', '" +
                    estudiante.getApellido() + "', '" + estudiante.getEmail() + 
                    "', '" + estudiante.getFoto() + "')");
            for (Curso curso : estudiante.getCursos()) {
                res = stm.executeUpdate("insert into estudiante_curso values (" +
                        estudiante.getId() + ", " + curso.getId() + ")");
            }
            if(res != 0)
                act = true;
            stm.close();
            c.close();
        }
        catch(Exception e) {
            this.error = e.getMessage();
        }
        return act;
    }
    
    /**
     * Permite registrar un nuevo curso en la base de datos.
     * @param curso Curso a registrar.
     * @return Estado de la trasacción, true si la transacción finalizó de forma
     *         correcta, false en caso contrario.
     */
    public boolean registrarCurso(Curso curso) {
        boolean reg = false;
        try {
            Connection c = con.conectar();
            Statement stm = c.createStatement();
            int res = stm.executeUpdate("insert into curso values (" + 
                    curso.getId() + ", '" + curso.getNombre() + "')");
            if(res != 0)
                reg = true;
            stm.close();
            c.close();
        }
        catch(Exception e) {
            this.error = e.getMessage();
        }
        return reg;
    }

}
