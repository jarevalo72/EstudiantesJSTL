/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para establecer la conexión con la bases de datos usando driver para
 * Microsoft SQLServer
 * @author Ing. Jorge A. Arévalo A.
 */
public class Conexion {
    /** Conexion */
    private Connection conn;
    /** Driver para la conexión */
    public String driver;
    /* URL para la conexión */
    private String url;
    /** Usuario para autenticar la conexión */
    private String user;
    /** Password para autenticar la conexión */
    private String pwd;

    /**
     * Constructor con parametros para crear la conexión.
     * @param driver Driver para la conexión.
     * @param url URL para la conexión.
     * @param user Usuario para autenticar la conexión.
     * @param pwd Password para autenticar la conexión.
     */
    public Conexion(String driver, String url, String user, String pwd) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    /**
     * Constructor por defecto para cear la conexión.
     */
    public Conexion() {
        this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        this.url = "jdbc:sqlserver://localhost:1433;databaseName=Test";
        this.user = "sa";
        this.pwd = "w4dm1n";
    }

    public Connection getConn() {
        return conn;
    }

    /**
     * Permite establecer la conexión con la base de datos.
     * @return Conexión lógica con la base de datos.
     * @throws SQLException Exception en la conexión con la base de datos.
     */
    public Connection conectar() throws SQLException {
        if(this.conn == null || this.conn.isClosed()) {
            try {
                Class.forName(this.driver);
                this.conn = DriverManager.getConnection(url, this.user, this.pwd);
            }
            catch(Exception ex) {
                ex.printStackTrace();;
            }
        }
        return this.conn;
    }
    
    /**
     * Permite cerrar la conexión lógica con la case de datos.
     * @throws SQLException Excepción en el cierre de la conexión.
     */
    public void desconectar() throws SQLException {
        if(this.conn != null && !this.conn.isClosed())
            this.conn.close();
    }
    
}
