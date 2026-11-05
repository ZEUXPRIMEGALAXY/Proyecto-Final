/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public class RAM extends Componente {
    private String tipo;
    private int capacidad;
    private int mhz;

    public RAM(int id, double precio, int stock, String marca, String modelo, int consumoWatts, String tipo, int capacidad, int mhz) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.mhz = mhz;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public int getMhz() { return mhz; }
    public void setMhz(int mhz) { this.mhz = mhz; }

    @Override
    public String mostrarEspecificaciones() {
        return "RAM{tipo=" + tipo + ", capacidad=" + capacidad + ", mhz=" + mhz + ", precio=" + precio + ", marca=" + marca + ", modelo=" + modelo + ", consumoWatts=" + consumoWatts + "}";
    }
}
