/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public abstract class Almacenamiento extends Componente {
    protected int capacidadGB;
    protected String formato;

    public Almacenamiento(int capacidadGB, String formato, int id, double precio, int stock, String marca, String modelo, int consumoWatts) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.capacidadGB = capacidadGB;
        this.formato = formato;
    }

    public int getCapacidadGB() {
        return capacidadGB;
    }

    public void setCapacidadGB(int capacidadGB) {
        this.capacidadGB = capacidadGB;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

   
    
    
    
}
