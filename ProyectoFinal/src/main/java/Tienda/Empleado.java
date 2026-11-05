/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

import java.math.BigInteger;

/**
 *
 * @author Personal
 */
public class Empleado extends Persona {
    private String telefono;

    public Empleado(String nombre, int id, String telefono) {
        super(nombre, id);
        this.telefono = telefono;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Empleado: " + nombre + " | ID: " + id + " | Telefono: " + telefono);
    }
    
    public String getTelefono() { return telefono; }
}
