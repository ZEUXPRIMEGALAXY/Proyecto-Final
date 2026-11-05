/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

/**
 *
 * @author Personal
 */
import java.util.ArrayList;

public class Cliente extends Persona {
    private ArrayList<Venta> compras; 

    public Cliente(String nombre, int id) {
        super(nombre, id);
        this.compras = new ArrayList<>();
    }

    public void agregarCompra(Venta venta) {
        this.compras.add(venta);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Cliente: " + nombre + " | DNI: " + id + " | Historial de compras: " + compras.size());
    }
}
