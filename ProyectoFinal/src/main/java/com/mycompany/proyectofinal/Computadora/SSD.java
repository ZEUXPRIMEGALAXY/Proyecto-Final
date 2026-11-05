/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public class SSD extends Almacenamiento {

    public SSD(int id, double precio, int stock, String marca, String modelo, int consumoWatts,int capacidadGB, String formato) {
        super(capacidadGB, formato, id, precio, stock, marca, modelo, consumoWatts);
    }

    @Override
    public String mostrarEspecificaciones() {
        return "SSD{capacidadGB=" + capacidadGB + ", formato=" + formato + ", precio=" + precio + ", marca=" + marca + ", modelo=" + modelo + ", consumoWatts=" + consumoWatts + "}";
    }
}

