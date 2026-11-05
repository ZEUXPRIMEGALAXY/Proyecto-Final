/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public class CPU extends Componente {
    private String socket;
    private int nucleos;

    public CPU(int id, double precio, int stock, String marca, String modelo, int consumoWatts, String socket, int nucleos) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.socket = socket;
        this.nucleos = nucleos;
    }

    public String getSocket() { return socket; }
    public void setSocket(String socket) { this.socket = socket; }
    public int getNucleos() { return nucleos; }
    public void setNucleos(int nucleos) { this.nucleos = nucleos; }

    @Override
    public String mostrarEspecificaciones() {
        return "CPU{socket=" + socket + ", nucleos=" + nucleos + ", precio=" + precio + ", marca=" + marca + ", modelo=" + modelo + ", consumoWatts=" + consumoWatts + "}";
    }
}
