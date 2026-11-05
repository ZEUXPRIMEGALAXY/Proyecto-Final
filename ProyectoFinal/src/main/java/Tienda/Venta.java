/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

/**
 *
 * @author Personal
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private LocalDate fecha;
    private Cliente cliente;
    private Empleado empleado;
    private Factura factura;

    public Venta(Cliente cliente, Empleado empleado) {
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.empleado = empleado;
    }

    // Método adaptado de tu original: registra la venta, crea la factura y la guarda en el cliente
    public void registrarVenta(double total, String detalle) {
        // 1. Composición: La venta crea su propia factura
        this.factura = new Factura(total, detalle);
        
        // 2. Asociación: Guardamos esta venta en el historial del cliente
        this.cliente.agregarCompra(this);
        
        System.out.println("\n>>> TRANSACCION REGISTRADA <<<");
        System.out.println("Fecha: " + this.fecha);
        System.out.println("Atendido por: " + this.empleado.getNombre());
        System.out.println("Cliente: " + this.cliente.getNombre());
        
        // 3. Imprimimos el ticket final
        this.factura.imprimirFactura();
    }

    // Getters
    public LocalDate getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public Factura getFactura() { return factura; }
}
