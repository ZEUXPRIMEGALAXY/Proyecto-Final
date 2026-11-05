/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

import Excepciones.PosicionInvalidaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximo
 */
public class Gabinete extends Componente {
    private String CapacidadTamañoPlacaMadre;
    private List<Componente> componentesInstalados;

    public Gabinete(int id, double precio, int stock, String marca, String modelo, int consumoWatts, String CapacidadTamañoPlacaMadre) {
        super(id, precio, stock, marca, modelo, consumoWatts);
        this.CapacidadTamañoPlacaMadre = CapacidadTamañoPlacaMadre;
        this.componentesInstalados = new ArrayList<>();
    }

    public String getCapacidadTamañoPlacaMadre() { return CapacidadTamañoPlacaMadre; }
    public void setCapacidadTamañoPlacaMadre(String CapacidadTamañoPlacaMadre) { this.CapacidadTamañoPlacaMadre = CapacidadTamañoPlacaMadre; }
    public List<Componente> getComponentesInstalados() { return componentesInstalados; }

    public void instalarComponente(Componente comp) {
        if (comp != null) {
            this.componentesInstalados.add(comp);
        }
    }

    public void quitarComponentePorIndice(int indice) throws PosicionInvalidaException {
        if (indice < 0 || indice >= this.componentesInstalados.size()) {
            throw new PosicionInvalidaException("Posición inválida en el gabinete: el índice " + indice + " no existe.");
        }
        this.componentesInstalados.remove(indice);
    }

    @Override
    public String mostrarEspecificaciones() {
        return "Gabinete{CapacidadTamañoPlacaMadre=" + CapacidadTamañoPlacaMadre + ", cantidadComponentes=" + componentesInstalados.size() + "}";
    }
}
