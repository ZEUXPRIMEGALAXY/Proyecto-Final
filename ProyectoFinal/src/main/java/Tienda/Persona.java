/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

/**
 *
 * @author Personal
 */
public abstract class Persona {
    protected String nombre;
    protected int id;

    public Persona(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }
    
    public abstract void mostrarDatos();
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
}
