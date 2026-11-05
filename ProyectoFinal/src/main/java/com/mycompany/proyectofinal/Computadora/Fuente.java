/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public class Fuente extends Componente {
    private int PoderFuente;
    private String Certificado;

    public Fuente(int id, double precio, int stock, String marca, String modelo, int consumoWatts, int PoderFuente, String Certificado) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.PoderFuente = PoderFuente;
        this.Certificado = Certificado;
    }
    public int getPoderFuente() { return PoderFuente; }
    public void setPoderFuente(int PoderFuente) { this.PoderFuente = PoderFuente; }
    public String getCertificado() { return Certificado; }
    public void setCertificado(String Certificado) { this.Certificado = Certificado; }

    @Override
    public String mostrarEspecificaciones() {
        return "Fuente{PoderFuente=" + PoderFuente + ", Certificado=" + Certificado + ", precio=" + precio + ", marca=" + marca + ", modelo=" + modelo + ", consumoWatts=" + consumoWatts + "}";
    }
}
