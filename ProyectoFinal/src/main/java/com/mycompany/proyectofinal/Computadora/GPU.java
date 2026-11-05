/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public class GPU extends Componente {
    private int VRAM;

    public GPU(int id, double precio, int stock, String marca, String modelo, int consumoWatts, int VRAM) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.VRAM = VRAM;
    }

    public int getVRAM() { return VRAM; }
    public void setVRAM(int VRAM) { this.VRAM = VRAM; }

    @Override
    public String mostrarEspecificaciones() {
        return "GPU{VRAM=" + VRAM + ", precio=" + precio + ", marca=" + marca + ", modelo=" + modelo + ", consumoWatts=" + consumoWatts + "}";
    }
}
