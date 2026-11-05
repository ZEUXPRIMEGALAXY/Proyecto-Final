/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

/**
 *
 * @author Personal
 */
public class Factura {
    private double total;
    private String detalle;

    public Factura(double total, String detalle) {
        this.total = total;
        this.detalle = detalle;
    }

    public void imprimirFactura() {
        System.out.println("======================================");
        System.out.println("               FACTURA                ");
        System.out.println("======================================");
        System.out.println("Detalle: " + this.detalle);
        System.out.println("Total a pagar: $" + this.total);
        System.out.println("======================================\n");
    }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
}
