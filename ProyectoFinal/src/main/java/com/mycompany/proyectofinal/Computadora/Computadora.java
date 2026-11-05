/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

import Excepciones.PosicionInvalidaException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Maximo
 */
public class Computadora {
    private int id;
    private String nombre;
    private Gabinete gabinete; // Relación de Composición / Agregación
    private List<Componente> componentes;

    public Computadora(int id, String nombre, Gabinete gabinete) {
        this.id = id;
        this.nombre = nombre;
        this.gabinete = gabinete;
        this.componentes = new ArrayList<>();
        if (gabinete != null) {
            this.componentes.add(gabinete);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Gabinete getGabinete() {
        return gabinete;
    }

    public void setGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
    
    public void agregarComponente(Componente comp) {
        if (comp != null) {
            this.componentes.add(comp);
            if (this.gabinete != null && comp != this.gabinete) {
                this.gabinete.instalarComponente(comp);
            }
        }
    }
    
    public void quitarComponente(int indice) throws PosicionInvalidaException {
       this.gabinete.quitarComponentePorIndice(indice);
    }
    
    public double calcularPrecioTotal() {
        double total = 0.0;
        Iterator<Componente> it = this.componentes.iterator();
        while (it.hasNext()) {
            total += it.next().getPrecio();
        }
        return total;
        
    }
    public int calcularConsumoTotalWatts() {
        int consumo = 0;
        for (Componente c : componentes) {
            consumo += c.getConsumoWatts();
        }
        return consumo;
    }
    
    
    public String mostrarFichaTecnica() {
        String linea = "========================================\n";
        StringBuilder ficha = new StringBuilder(linea);
        ficha.append("Ficha Técnica: ").append(nombre).append(" (ID: ").append(id).append(")\n");
        ficha.append("Consumo estimado: ").append(calcularConsumoTotalWatts()).append(" W\n");
        ficha.append("Precio total del ensamble: $").append(calcularPrecioTotal()).append("\n");
        ficha.append("Componentes instalados:\n");
        for (Componente c : componentes) {
            ficha.append(" -> ").append(c.mostrarEspecificaciones()).append("\n");
        }
        ficha.append(linea);
        return ficha.toString();
    }
    
    
  
}