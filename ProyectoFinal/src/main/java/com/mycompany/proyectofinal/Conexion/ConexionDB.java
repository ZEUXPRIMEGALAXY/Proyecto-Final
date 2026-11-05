/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Maximo
 */
public class ConexionDB {
    private Connection con;

    public ConexionDB() {
        this.con = null;
    }

    public Connection getCon() {
        return con;
    }
    
    public void Conectar() {
        try {
            if (this.con == null || this.con.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                this.con = DriverManager.getConnection("jdbc:sqlite:Tienda.sqlite");
                System.out.println("Conexión a sistema abierta");
            }
        } catch (SQLException a) {
            System.out.println("Error al abrir la conexión: " + a.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: no se encuentran las clases sqlite o el driver: " + ex.getMessage());
        }
    }
    
    public void Desconectar() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
                System.out.println("Conexión a sistema cerrada");
            }
        } catch (SQLException a) {
             System.out.println("Error al cerrar la conexión: " + a.getMessage());
        }
    }
}
