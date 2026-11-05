/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public class PlacaMadre extends Componente {
    private String socketCPU;
    private String TipoRAM;

    public PlacaMadre(int id, double precio, int stock, String marca, String modelo, int consumoWatts, String socketCPU, String TipoRAM) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.socketCPU = socketCPU;
        this.TipoRAM = TipoRAM;
    }

    public String getSocketCPU() { return socketCPU; }
    public void setSocketCPU(String socketCPU) { this.socketCPU = socketCPU; }
    public String getTipoRAM() { return TipoRAM; }
    public void setTipoRAM(String TipoRAM) { this.TipoRAM = TipoRAM; }

    @Override
    public String mostrarEspecificaciones() {
        return "PlacaMadre{socketCPU=" + socketCPU + ", TipoRAM=" + TipoRAM + ", precio=" + precio + ", marca=" + marca + ", modelo=" + modelo + ", consumoWatts=" + consumoWatts + "}";
    }
}
