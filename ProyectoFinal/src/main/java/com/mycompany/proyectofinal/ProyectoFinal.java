/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal;
import Excepciones.PosicionInvalidaException;
import Excepciones.StockInsuficienteException;
import com.mycompany.proyectofinal.Computadora.*;
import Tienda.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Maximo
 */
public class ProyectoFinal {

    public static void main(String[] args) throws PosicionInvalidaException, StockInsuficienteException {
         // ===== Gestores =====
        GestorComponentes gestorHardware = new GestorComponentes(
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        GestorPersonas gestorPersonas = new GestorPersonas();
        
        Tienda tienda = new Tienda(gestorHardware, gestorPersonas);
        tienda.iniciarTienda();
 
        gestorHardware.cerrarConexion();
    }
}
        
   
