/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

/**
 *
 * @author Maximo
 */
public abstract class Componente {
    protected int id;
    protected double precio;
    protected int stock;
    protected String marca;
    protected String modelo;
    protected int consumoWatts;

    public Componente(int id, double precio, int stock, String marca, String modelo, int consumoWatts) {
        this.id = id;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        this.modelo = modelo;
        this.consumoWatts = consumoWatts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getConsumoWatts() {
        return consumoWatts;
    }

    public void setConsumoWatts(int consumoWatts) {
        this.consumoWatts = consumoWatts;
    }
    
    
public abstract String mostrarEspecificaciones();    
    
    
}
