/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

import Excepciones.StockInsuficienteException;
import com.mycompany.proyectofinal.Computadora.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 * @author Maximo
 */
public class Tienda {
    private GestorComponentes gestorHardware;
    private GestorPersonas gestorPersonas;
    public List<String> tablasValidas = Arrays.asList("cpu", "gpu", "ram", "ssd", "gabinete", "fuente", "placamadre");
    public String tabla = "";
    Scanner sc = new Scanner(System.in);

    public Tienda(GestorComponentes gestorHardware, GestorPersonas gestorPersonas) {
        this.gestorHardware = gestorHardware;
        this.gestorPersonas = gestorPersonas;
    }
    
    public void iniciarTienda() throws StockInsuficienteException {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==========================================");
            System.out.println("      BIENVENIDO AL SISTEMA DE GESTION    ");
            System.out.println("==========================================");
            System.out.println("1. Ingresar como Empleado (Gestion de Inventario)");
            System.out.println("2. Ingresar como Cliente (Tienda y Compras)");
            System.out.println("3. Registrar nuevo usuario (Cliente/Empleado)");
            System.out.println("4. Salir del sistema");
            System.out.print("Seleccione su rol: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese su ID de Empleado: ");
                        Empleado emp = gestorPersonas.obtenerEmpleadoPorId(Integer.parseInt(sc.nextLine()));
                        if (emp != null) {
                            menuEmpleado(sc, emp);
                        } else {
                            System.out.println("Error: Empleado no encontrado.");
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese su DNI de Cliente: ");
                        Cliente cli = gestorPersonas.obtenerClientePorId(Integer.parseInt(sc.nextLine()));
                        if (cli != null) {
                            menuCliente(sc, cli);
                        } else {
                            System.out.println("Error: Cliente no encontrado.");
                        }
                        break;
                    case 3:
                        registrarPersona(sc);
                        break;
                    case 4:
                        salir = true;
                        System.out.println("Cerrando sistema...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }

    private void menuEmpleado(Scanner sc, Empleado empleado) throws StockInsuficienteException {
        boolean volver = false;
        String categoria=null;
        int id;
        while (!volver) {
            System.out.println("\n--- PANEL DE CONTROL: " + empleado.getNombre().toUpperCase() + " ---");
            System.out.println("1. Ver Inventario Completo");
            System.out.println("2. Agregar nuevo componente al inventario");
            System.out.println("3. Eliminar componente del inventario");
            System.out.println("4. Modificar stock/precio de un componente");
            System.out.println("5. Cerrar sesion y volver al inicio");
            System.out.print("Opcion: ");

            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> gestorHardware.mostrarInventarioCompleto();
                case 2 -> {
                    // Aquí llamás al método de tu GestorComponentes que hace el INSERT en SQLite
                    System.out.println("Que desea añadir?");
                    categoria = verificacion(sc);
                    gestorHardware.crearComponenteGeneral(categoria, sc);
                }
                case 3 -> {
                    System.out.println("--- Eliminar Componente ---");
                    Componente C = null;
                    id=0;
    
                     // Bucle que se repite mientras NO se encuentre el componente
                      while (C == null) {
                      System.out.println("Ingrese la categoría y ID:");
                      categoria = verificacion(sc);
                      id = Integer.parseInt(sc.nextLine());
        
                      C = gestorHardware.obtenerComponenteGeneral(categoria, id);
        
                      if (C == null) {
                         System.out.println("[Error] No se encontró un componente con ese ID en la categoría " + categoria + ". Intente nuevamente.");
                      }
    }
    
                       // Una vez que salió del while, significa que C NO es null
                       System.out.println("Se va a eliminar:");
                       C.mostrarEspecificaciones();
                       gestorHardware.eliminarComponentePorId(categoria, id);
                       System.out.println("¡Componente eliminado con éxito!");
                }
                case 4 -> {
                    System.out.println("Ingrese la categoria, id del producto y la cantidad en componentes que quiere modificar");
                    categoria = verificacion(sc);
                    id=Integer.parseInt(sc.nextLine());
                    int cantidad = Integer.parseInt(sc.nextLine());
                    if(gestorHardware.verificarYDescontarStock(categoria, id,cantidad) == false){
                        System.out.println("No se pudo modificar el stock");
                    }else{
                        System.out.println("Se modifico el stock");
                    }
                }
                case 5 -> volver = true;
            }
        }
    }


    private void menuCliente(Scanner sc, Cliente cliente) {
        boolean volver = false;
        
    
        Empleado vendedorAsignado = gestorPersonas.obtenerEmpleadoPorId(1); 
        if (vendedorAsignado == null) {
            System.out.println("Atención: No hay vendedores registrados en la base de datos (ID 1). Registre uno primero.");
            return;
        }

        while (!volver) {
            System.out.println("\n--- TIENDA EN LINEA: HOLA, " + cliente.getNombre().toUpperCase() + " ---");
            System.out.println("1. Ver catalogo de productos");
            System.out.println("2. Comprar componente suelto");
            System.out.println("3. Asistente para armar PC a medida");
            System.out.println("4. Cerrar sesion");
            System.out.print("Opcion: ");

            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    gestorHardware.mostrarInventarioCompleto();
                    break;
                case 2:
                    venderComponenteSuelto(sc, cliente, vendedorAsignado);
                    break;
                case 3:
                    venderPCCompleta(sc, cliente, vendedorAsignado);
                    break;
                case 4:
                    volver = true;
                    break;
            }
        }
    }


    private void venderComponenteSuelto(Scanner sc, Cliente cliente, Empleado empleado) {
    System.out.println("\n--- COMPRA INDIVIDUAL ---");
    String categoria = verificacion(sc);

    try {
        System.out.print("ID del componente que desea comprar: ");
        int idComp = Integer.parseInt(sc.nextLine());
        System.out.print("Cantidad que desea comprar: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        Componente c = gestorHardware.obtenerComponenteGeneral(categoria, idComp);
        if (c == null) {
            System.out.println("No existe un componente con ID " + idComp + " en " + categoria);
            return;
        }

        if (gestorHardware.verificarYDescontarStock(categoria, idComp, cantidad)) {
            Venta venta = new Venta(cliente, empleado);
            venta.registrarVenta(c.getPrecio() * cantidad,
                "Ha comprado: " + cantidad + " de " + categoria.toUpperCase() + " (ID: " + c.getId() + ")");
        }
    } catch (StockInsuficienteException e) {
        System.out.println("\n[OPERACION DENEGADA] " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("\n[ERROR] Debe ingresar un numero valido.");
    }
}

    private void venderPCCompleta(Scanner sc, Cliente cliente, Empleado empleado) {
        System.out.println("\n--- ASISTENTE DE ENSAMBLE ---");
        System.out.print("Nombre para identificar su nueva PC: ");
        String nombrePc = sc.nextLine().trim();
        if (nombrePc.isEmpty()) {
            System.out.println("[ERROR] La PC debe tener un nombre. Armado cancelado.");
            return;
        }

        try {
            // ===== FASE 1: elegir y validar las piezas (todavia NO se toca el stock) =====
            System.out.print("Seleccione ID del Gabinete: ");
            Gabinete gabinete = gestorHardware.obtenerGabinetePorId(Integer.parseInt(sc.nextLine().trim()));
            if (gabinete == null) { piezaInexistente("El gabinete"); return; }

            System.out.print("Seleccione ID del Procesador (CPU): ");
            CPU cpu = gestorHardware.obtenerCpuPorId(Integer.parseInt(sc.nextLine().trim()));
            if (cpu == null) { piezaInexistente("El procesador"); return; }

            System.out.print("Seleccione ID de la Placa Madre: ");
            PlacaMadre placa = gestorHardware.obtenerPlacaMadrePorId(Integer.parseInt(sc.nextLine().trim()));
            if (placa == null) { piezaInexistente("La placa madre"); return; }

            System.out.print("Seleccione ID de la RAM: ");
            RAM ram = gestorHardware.obtenerRamPorId(Integer.parseInt(sc.nextLine().trim()));
            if (ram == null) { piezaInexistente("La RAM"); return; }

            System.out.print("Seleccione ID de la Fuente: ");
            Fuente fuente = gestorHardware.obtenerFuentePorId(Integer.parseInt(sc.nextLine().trim()));
            if (fuente == null) { piezaInexistente("La fuente"); return; }

            // GPU y SSD son opcionales: 0 para omitir
            System.out.print("Seleccione ID de la GPU (0 para omitir): ");
            int idGpu = Integer.parseInt(sc.nextLine().trim());
            GPU gpu = null;
            if (idGpu != 0) {
                gpu = gestorHardware.obtenerGpuPorId(idGpu);
                if (gpu == null) { piezaInexistente("La GPU"); return; }
            }

            System.out.print("Seleccione ID del SSD (0 para omitir): ");
            int idSsd = Integer.parseInt(sc.nextLine().trim());
            SSD ssd = null;
            if (idSsd != 0) {
                ssd = gestorHardware.obtenerSsdPorId(idSsd);
                if (ssd == null) { piezaInexistente("El SSD"); return; }
            }

            // ===== Compatibilidad =====
            if (!mismoValor(cpu.getSocket(), placa.getSocketCPU())) {
                System.out.println("[INCOMPATIBLE] El CPU (socket " + cpu.getSocket()
                        + ") no entra en la placa madre (socket " + placa.getSocketCPU() + ").");
                return;
            }
            if (!mismoValor(ram.getTipo(), placa.getTipoRAM())) {
                System.out.println("[INCOMPATIBLE] La RAM (" + ram.getTipo()
                        + ") no es compatible con la placa madre (" + placa.getTipoRAM() + ").");
                return;
            }

            // ===== Ensamble en memoria =====
            Computadora pc = new Computadora(1, nombrePc, gabinete);
            pc.agregarComponente(cpu);
            pc.agregarComponente(placa);
            pc.agregarComponente(ram);
            pc.agregarComponente(fuente);
            if (gpu != null) pc.agregarComponente(gpu);
            if (ssd != null) pc.agregarComponente(ssd);

            int consumo = pc.calcularConsumoTotalWatts();
            if (consumo > fuente.getPoderFuente()) {
                System.out.println("[INCOMPATIBLE] El consumo estimado (" + consumo
                        + " W) supera la potencia de la fuente (" + fuente.getPoderFuente() + " W).");
                return;
            }

            // ===== FASE 2: descontar stock de TODAS las piezas en una sola transaccion =====
            // Si falta stock de cualquiera, se revierte todo y no se vende nada.
            Map<String, Integer> piezas = new LinkedHashMap<>();
            piezas.put("gabinete", gabinete.getId());
            piezas.put("cpu", cpu.getId());
            piezas.put("placamadre", placa.getId());
            piezas.put("ram", ram.getId());
            piezas.put("fuente", fuente.getId());
            if (gpu != null) piezas.put("gpu", gpu.getId());
            if (ssd != null) piezas.put("ssd", ssd.getId());

            if (!gestorHardware.descontarStockEnLote(piezas)) {
                System.out.println("[ERROR] No se pudo concretar la compra. El stock no fue modificado.");
                return;
            }

            // ===== FASE 3: registrar la venta =====
            StringJoiner detalle = new StringJoiner(", ", nombrePc + " (", ")");
            for (Componente c : pc.getComponentes()) {
                detalle.add(c.getMarca() + " " + c.getModelo());
            }

            Venta venta = new Venta(cliente, empleado);
            venta.registrarVenta(pc.calcularPrecioTotal(), detalle.toString());
            System.out.println(pc.mostrarFichaTecnica());

        } catch (StockInsuficienteException e) {
            System.out.println("\n[ERROR AL ARMAR PC] " + e.getMessage());
            System.out.println("No se concreto la compra y el stock no fue modificado.");
        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] Debe ingresar un numero valido. Armado cancelado, el stock no fue modificado.");
        }
    }

    private void piezaInexistente(String pieza) {
        System.out.println("[ERROR] " + pieza + " seleccionado no existe. Armado cancelado, el stock no fue modificado.");
    }

    private boolean mismoValor(String a, String b) {
        return a != null && a.equalsIgnoreCase(b);
    }

    private void registrarPersona(Scanner sc) {
        // (El mismo código de registro del paso anterior para crear Clientes/Empleados)
        System.out.println("\n--- REGISTRO ---");
        System.out.println("1. Cliente | 2. Empleado");
        System.out.print("Opcion: ");
        int op = Integer.parseInt(sc.nextLine());
        System.out.print("ID/DNI: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        
        if(op == 1) {
            gestorPersonas.guardarCliente(new Cliente(nombre, id));
            System.out.println("Cliente registrado.");
        } else if (op == 2) {
            System.out.print("Telefono: ");
            gestorPersonas.guardarEmpleado(new Empleado(nombre, id, sc.nextLine()));
            System.out.println("Empleado registrado.");
        }
    }
    
    private String verificacion(Scanner sc) {
    while (true) {
        System.out.print("Categoria del componente (cpu, gpu, ram, ssd, gabinete, fuente, placamadre): ");
        String categoria = sc.nextLine().toLowerCase().trim();
        if (tablasValidas.contains(categoria)) {
            return categoria;
        }
        System.out.println("Error: Categoria no reconocida. Intente de nuevo.\n");
    }
}
}