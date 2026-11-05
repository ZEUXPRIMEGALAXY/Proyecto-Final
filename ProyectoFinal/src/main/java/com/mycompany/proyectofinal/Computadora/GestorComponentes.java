/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.Computadora;

import Excepciones.StockInsuficienteException;
import com.mycompany.proyectofinal.Conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Maximo
 */
public class GestorComponentes {
    private ConexionDB conexionDB;
    private List<CPU> listaCPU;
    private List<Fuente> listaFuente;
    private List<Gabinete> listaGabinete;
    private List<GPU> listaGPU;
    private List<PlacaMadre> listaPlacaMadre;
    private List<RAM> listaRAM;
    private List<SSD> listaSSD;
    private static final Set<String> TABLAS_VALIDAS =
            Set.of("cpu", "gpu", "ram", "ssd", "gabinete", "fuente", "placamadre");

    public GestorComponentes( List<CPU> listaCPU, List<Fuente> listaFuente, List<Gabinete> listaGabinete, List<GPU> listaGPU, List<PlacaMadre> listaPlacaMadre, List<RAM> listaRAM, List<SSD> listaSSD) {
        this.conexionDB = new ConexionDB();
        this.conexionDB.Conectar();
        this.listaCPU = listaCPU;
        this.listaFuente = listaFuente;
        this.listaGabinete = listaGabinete;
        this.listaGPU = listaGPU;
        this.listaPlacaMadre = listaPlacaMadre;
        this.listaRAM = listaRAM;
        this.listaSSD = listaSSD;
        CrearTablas();
    }

    private void CrearTablas() {
        String sqlCPU = "CREATE TABLE IF NOT EXISTS cpu (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, socket TEXT, nucleos INTEGER)";
        String sqlFuente = "CREATE TABLE IF NOT EXISTS fuente (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, poderfuente INTEGER, certificacion TEXT)";
        String sqlGabinete = "CREATE TABLE IF NOT EXISTS gabinete (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, TamañoPlacaBase TEXT)"; 
        String sqlGPU = "CREATE TABLE IF NOT EXISTS gpu (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, vRam INTEGER)";
        String sqlPlacaMadre = "CREATE TABLE IF NOT EXISTS placamadre (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, socket TEXT, tiporam TEXT)";
        String sqlRAM = "CREATE TABLE IF NOT EXISTS ram (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, tipo TEXT, capacidadGB INTEGER, mhz INTEGER)";
        String sqlSSD = "CREATE TABLE IF NOT EXISTS ssd (id INTEGER PRIMARY KEY, precio REAL, stock INTEGER, marca TEXT, modelo TEXT, watts INTEGER, capacidadGB INTEGER, formato TEXT)";

        try (Statement stmt = conexionDB.getCon().createStatement()) {
            stmt.execute(sqlCPU);
            stmt.execute(sqlFuente);
            stmt.execute(sqlGabinete);
            stmt.execute(sqlGPU);
            stmt.execute(sqlPlacaMadre);
            stmt.execute(sqlRAM);
            stmt.execute(sqlSSD);
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }
    
    private void asignarParametrosBase(PreparedStatement pstmt, Componente comp) throws SQLException {
        pstmt.setInt(1, comp.getId());
        pstmt.setDouble(2, comp.getPrecio());
        pstmt.setInt(3, comp.getStock());
        pstmt.setString(4, comp.getMarca());
        pstmt.setString(5, comp.getModelo());
        pstmt.setInt(6, comp.getConsumoWatts()); 
    }
    public void guardarCPU(CPU cpu) {
        String sql = "INSERT INTO cpu(id, precio, stock, marca, modelo, watts, socket, nucleos) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, cpu);
            pstmt.setString(7, cpu.getSocket());
            pstmt.setInt(8, cpu.getNucleos());
            pstmt.executeUpdate();
            listaCPU.add(cpu);
        } catch (SQLException e) { System.out.println("Error al guardar CPU: " + e.getMessage()); }
    }

    public void guardarGPU(GPU gpu) {
        String sql = "INSERT INTO gpu(id, precio, stock, marca, modelo, watts, vRam) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, gpu);
            pstmt.setInt(7, gpu.getVRAM());
            pstmt.executeUpdate();
            listaGPU.add(gpu);
        } catch (SQLException e) { System.out.println("Error al guardar GPU: " + e.getMessage()); }
    }

    public void guardarPlacaMadre(PlacaMadre placa) {
        String sql = "INSERT INTO placamadre(id, precio, stock, marca, modelo, watts, socket, tiporam) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, placa);
            pstmt.setString(7, placa.getSocketCPU());
            pstmt.setString(8, placa.getTipoRAM());
            pstmt.executeUpdate();
            listaPlacaMadre.add(placa);
        } catch (SQLException e) { System.out.println("Error al guardar Placa Madre: " + e.getMessage()); }
    }

    public void guardarRAM(RAM ram) {
        String sql = "INSERT INTO ram(id, precio, stock, marca, modelo, watts, tipo, capacidadGB, mhz) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, ram);
            pstmt.setString(7, ram.getTipo());
            pstmt.setInt(8, ram.getCapacidad());
            pstmt.setInt(9, ram.getMhz());
            pstmt.executeUpdate();
            listaRAM.add(ram);
        } catch (SQLException e) { System.out.println("Error al guardar RAM: " + e.getMessage()); }
    }

    public void guardarFuente(Fuente fuente) {
        String sql = "INSERT INTO fuente(id, precio, stock, marca, modelo, watts, poderfuente, certificacion) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, fuente);
            pstmt.setInt(7, fuente.getPoderFuente());
            pstmt.setString(8, fuente.getCertificado());
            pstmt.executeUpdate();
            listaFuente.add(fuente);
        } catch (SQLException e) { System.out.println("Error al guardar Fuente: " + e.getMessage()); }
    }

    public void guardarSSD(SSD ssd) {
        String sql = "INSERT INTO ssd(id, precio, stock, marca, modelo, watts, capacidadGB, formato) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, ssd);
            pstmt.setInt(7, ssd.getCapacidadGB());
            pstmt.setString(8, ssd.getFormato());
            pstmt.executeUpdate();
            listaSSD.add(ssd);
        } catch (SQLException e) { System.out.println("Error al guardar SSD: " + e.getMessage()); }
    }

   

    public void guardarGabinete(Gabinete gabinete) {
        String sql = "INSERT INTO gabinete(id, precio, stock, marca, modelo, watts, TamañoPlacaBase) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            asignarParametrosBase(pstmt, gabinete);
            pstmt.setString(7, gabinete.getCapacidadTamañoPlacaMadre());
            pstmt.executeUpdate();
            listaGabinete.add(gabinete);
        } catch (SQLException e) { System.out.println("Error al guardar Gabinete: " + e.getMessage()); }
    }
    
   public void cargarCPUsDesdeBD() {
        listaCPU.clear();
        String sql = "SELECT * FROM cpu";
        try (Statement stmt = conexionDB.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // El orden coincide con el constructor de tu clase CPU
                listaCPU.add(new CPU(
                    rs.getInt("id"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock"),
                    rs.getString("marca"), 
                    rs.getString("modelo"), 
                    rs.getInt("watts"),
                    rs.getString("socket"),  
                    rs.getInt("nucleos")     
                ));
            }
        } catch (SQLException e) { 
            System.out.println("Error al cargar CPUs: " + e.getMessage()); 
        }
    }
    public void cargarFuentesDesdeBD() {
        listaFuente.clear();
        String sql = "SELECT * FROM fuente";
        try (Statement stmt = conexionDB.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                listaFuente.add(new Fuente(
                    rs.getInt("id"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock"),
                    rs.getString("marca"), 
                    rs.getString("modelo"), 
                    rs.getInt("watts"),
                    rs.getInt("poderfuente"),       // Columna: poderfuente INTEGER
                    rs.getString("certificacion")   // Columna: certificacion TEXT
                ));
            }
        } catch (SQLException e) { 
            System.out.println("Error al cargar Fuentes: " + e.getMessage()); 
        }
    }

    public void cargarGabinetesDesdeBD() {
    listaGabinete.clear();
    String sql = "SELECT * FROM gabinete";
    try (Statement stmt = conexionDB.getCon().createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            listaGabinete.add(new Gabinete(
                rs.getInt("id"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getInt("watts"),
                rs.getString("TamañoPlacaBase")
            ));
        }
        
    } catch (SQLException e) {
        System.out.println("Error al cargar Gabinetes: " + e.getMessage());
    }
}

    public void cargarGPUsDesdeBD() {
        listaGPU.clear();
        String sql = "SELECT * FROM gpu";
        try (Statement stmt = conexionDB.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                listaGPU.add(new GPU(
                    rs.getInt("id"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock"),
                    rs.getString("marca"), 
                    rs.getString("modelo"), 
                    rs.getInt("watts"),
                    rs.getInt("vRam")  // Columna: vRam INTEGER (como lo pusiste al final)
                ));
            }
        } catch (SQLException e) { 
            System.out.println("Error al cargar GPUs: " + e.getMessage()); 
        }
    }

    public void cargarPlacasMadreDesdeBD() {
        listaPlacaMadre.clear();
        String sql = "SELECT * FROM placamadre";
        try (Statement stmt = conexionDB.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                listaPlacaMadre.add(new PlacaMadre(
                    rs.getInt("id"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock"),
                    rs.getString("marca"), 
                    rs.getString("modelo"), 
                    rs.getInt("watts"),
                    rs.getString("socket"),   // Columna: socket TEXT
                    rs.getString("tiporam")   // Columna: tiporam TEXT
                ));
            }
        } catch (SQLException e) { 
            System.out.println("Error al cargar Placas Madre: " + e.getMessage()); 
        }
    }

    public void cargarRAMsDesdeBD() {
        listaRAM.clear();
        String sql = "SELECT * FROM ram";
        try (Statement stmt = conexionDB.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                listaRAM.add(new RAM(
                    rs.getInt("id"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock"),
                    rs.getString("marca"), 
                    rs.getString("modelo"), 
                    rs.getInt("watts"),
                    rs.getString("tipo"),       // Columna: tipo TEXT
                    rs.getInt("capacidadGB"),   // Columna: capacidadGB INTEGER
                    rs.getInt("mhz")            // Columna: mhz INTEGER
                ));
            }
        } catch (SQLException e) { 
            System.out.println("Error al cargar RAMs: " + e.getMessage()); 
        }
    }

    public void cargarSSDsDesdeBD() {
        listaSSD.clear();
        String sql = "SELECT * FROM ssd";
        try (Statement stmt = conexionDB.getCon().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                listaSSD.add(new SSD(
                    rs.getInt("id"), 
                    rs.getDouble("precio"), 
                    rs.getInt("stock"),
                    rs.getString("marca"), 
                    rs.getString("modelo"), 
                    rs.getInt("watts"),
                    rs.getInt("capacidadGB"), // Columna: capacidadGB INTEGER
                    rs.getString("formato")   // Columna: formato TEXT
                ));
            }
        } catch (SQLException e) { 
            System.out.println("Error al cargar SSDs: " + e.getMessage()); 
        }
    }
    
    public void mostrarInventarioCompleto() {
        System.out.println("\n==================================================");
        System.out.println("          INVENTARIO COMPLETO DE HARDWARE         ");
        System.out.println("==================================================");

        // 1. Cargamos todo desde la base de datos para tener la información fresca
        cargarCPUsDesdeBD();
        cargarFuentesDesdeBD();
        cargarGabinetesDesdeBD();
        cargarGPUsDesdeBD();
        cargarPlacasMadreDesdeBD();
        cargarRAMsDesdeBD();
        cargarSSDsDesdeBD();

        // 2. Imprimimos cada lista utilizando polimorfismo
        System.out.println("\n--- Procesadores (CPU) ---");
        for (CPU c : listaCPU) { System.out.println("ID: " + c.getId() + " | " + c.mostrarEspecificaciones()); }
        if (listaCPU.isEmpty()) System.out.println("No hay CPUs registrados.");

        System.out.println("\n--- Fuentes de Poder ---");
        for (Fuente f : listaFuente) { System.out.println("ID: " + f.getId() + " | " + f.mostrarEspecificaciones()); }
        if (listaFuente.isEmpty()) System.out.println("No hay Fuentes registradas.");

        System.out.println("\n--- Gabinetes ---");
        for (Gabinete g : listaGabinete) { System.out.println("ID: " + g.getId() + " | " + g.mostrarEspecificaciones()); }
        if (listaGabinete.isEmpty()) System.out.println("No hay Gabinetes registrados.");

        System.out.println("\n--- Tarjetas de Video (GPU) ---");
        for (GPU gpu : listaGPU) { System.out.println("ID: " + gpu.getId() + " | " + gpu.mostrarEspecificaciones()); }
        if (listaGPU.isEmpty()) System.out.println("No hay GPUs registradas.");

        System.out.println("\n--- Placas Madre ---");
        for (PlacaMadre pm : listaPlacaMadre) { System.out.println("ID: " + pm.getId() + " | " + pm.mostrarEspecificaciones()); }
        if (listaPlacaMadre.isEmpty()) System.out.println("No hay Placas Madre registradas.");

        System.out.println("\n--- Memorias RAM ---");
        for (RAM r : listaRAM) { System.out.println("ID: " + r.getId() + " | " + r.mostrarEspecificaciones()); }
        if (listaRAM.isEmpty()) System.out.println("No hay Memorias RAM registradas.");

        System.out.println("\n--- Almacenamiento (SSD) ---");
        for (SSD s : listaSSD) { System.out.println("ID: " + s.getId() + " | " + s.mostrarEspecificaciones()); }
        if (listaSSD.isEmpty()) System.out.println("No hay SSDs registrados.");
        
        System.out.println("\n==================================================");
    }
    
    public boolean verificarYDescontarStock(String tabla, int id, int cantidad) throws StockInsuficienteException {
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return false;
        }
        String sqlSelect = "SELECT stock, marca, modelo FROM " + tabla + " WHERE id = ?";
        
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                String nombreComp = rs.getString("marca") + " " + rs.getString("modelo");
                
                if (stockActual < cantidad) {
                    throw new StockInsuficienteException("Stock agotado para el componente: " + nombreComp + " (ID: " + id + ")");
                }
                
                int nuevoStock = stockActual - cantidad;

                if (nuevoStock == 0) {
                // Se vendió todo: se elimina el componente de la tabla
                String sqlDelete = "DELETE FROM " + tabla + " WHERE id = ?";
        try (PreparedStatement pstmtDelete = conexionDB.getCon().prepareStatement(sqlDelete)) {
            pstmtDelete.setInt(1, id);
            pstmtDelete.executeUpdate();
            System.out.println("[INFO] " + nombreComp + " se agotó y fue eliminado del inventario.");
            return true;
        }
    }

    String sqlUpdate = "UPDATE " + tabla + " SET stock = ? WHERE id = ?";
    try (PreparedStatement pstmtUpdate = conexionDB.getCon().prepareStatement(sqlUpdate)) {
        pstmtUpdate.setInt(1, nuevoStock);
        pstmtUpdate.setInt(2, id);
        pstmtUpdate.executeUpdate();
        return true;
    }
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos al verificar stock: " + e.getMessage());
        }
        return false;
    }
    /**
     * Descuenta 1 unidad de cada pieza indicada (tabla -> id) en UNA sola transaccion.
     * Si alguna pieza no existe o no tiene stock, se revierte todo y se lanza
     * StockInsuficienteException. Igual que verificarYDescontarStock, si una pieza
     * llega a stock 0 se elimina del inventario.
     *
     * @return true si se descontaron todas; false si hubo un error de base de datos
     */
    public boolean descontarStockEnLote(Map<String, Integer> piezas) throws StockInsuficienteException {
        Connection con = conexionDB.getCon();
        try {
            con.setAutoCommit(false);
            for (Map.Entry<String, Integer> pieza : piezas.entrySet()) {
                String tabla = pieza.getKey().toLowerCase().trim();
                int id = pieza.getValue();
                if (!TABLAS_VALIDAS.contains(tabla)) {
                    throw new SQLException("Tabla no valida: " + pieza.getKey());
                }
                // Atomico: solo descuenta si todavia queda stock
                String sqlUpdate = "UPDATE " + tabla + " SET stock = stock - 1 WHERE id = ? AND stock >= 1";
                try (PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
                    ps.setInt(1, id);
                    if (ps.executeUpdate() == 0) {
                        con.rollback();
                        throw new StockInsuficienteException(
                                "Stock agotado o inexistente para " + tabla + " (ID: " + id + ")");
                    }
                }
                String sqlDelete = "DELETE FROM " + tabla + " WHERE id = ? AND stock <= 0";
                try (PreparedStatement ps = con.prepareStatement(sqlDelete)) {
                    ps.setInt(1, id);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("[INFO] " + tabla + " ID " + id + " se agoto y fue eliminado del inventario.");
                    }
                }
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ignorada) { }
            System.out.println("Error de base de datos al descontar stock: " + e.getMessage());
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException ignorada) { }
        }
    }

    public boolean eliminarComponentePorId(String tabla, int id) {
        String sql = "DELETE FROM " + tabla + " WHERE id = ?";
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar de " + tabla + ": " + e.getMessage());
            return false;
        }
    }
    
    public void buscarComponentePorFiltro(String tabla, String columna, String valorFiltro) {
        String sql = "SELECT * FROM " + tabla + " WHERE " + columna + " = ?";
        
        try (PreparedStatement pstmt = conexionDB.getCon().prepareStatement(sql)) {
            pstmt.setString(1, valorFiltro);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("--- Resultados en '" + tabla + "' (" + columna + " = " + valorFiltro + ") ---");
            boolean encontroAlgo = false;
            
            while (rs.next()) {
                encontroAlgo = true;
                // Como todas las tablas tienen id, marca, modelo y precio, podemos imprimirlos sin problema
                System.out.println("ID: " + rs.getInt("id") + " | " + rs.getString("marca") + " " + rs.getString("modelo") + " | Precio: $" + rs.getDouble("precio"));
            }
            
            if (!encontroAlgo) {
                System.out.println("No se encontraron resultados.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error en la búsqueda genérica: " + e.getMessage());
        }
    }
    public Componente obtenerComponenteGeneral(String tabla, int id) {
        switch (tabla.toLowerCase().trim()) {
            case "cpu":
                return obtenerCpuPorId(id); // Llama a los métodos que ya tenés hechos
            case "gpu":
                return obtenerGpuPorId(id);
            case "ram":
                return obtenerRamPorId(id);
            case "ssd":
                return obtenerSsdPorId(id);
            case "gabinete":
                return obtenerGabinetePorId(id);
            case "fuente":
                return obtenerFuentePorId(id);
            case "placamadre":
                return obtenerPlacaMadrePorId(id);
            default:
                return null;
        }
    }
    public Componente crearComponenteGeneral(String tabla, Scanner sc) {
        String categoria = tabla.toLowerCase().trim();
        switch (categoria) {
            case "cpu":
            case "gpu":
            case "ram":
            case "ssd":
            case "gabinete":
            case "fuente":
            case "placamadre":
                break;
            default:
                System.out.println("Categoría no reconocida: " + tabla);
                return null;
        }

        System.out.println("\n--- Nuevo componente: " + categoria.toUpperCase() + " ---");

        // Datos comunes a todos los componentes (los de la clase Componente)
        int id = leerIdNuevo(categoria, sc);
        double precio = leerDouble("Precio: ", 0, sc);
        int stock = leerEntero("Stock: ", 0, sc);
        String marca = leerTexto("Marca: ", sc);
        String modelo = leerTexto("Modelo: ", sc);
        int watts = leerEntero("Consumo (Watts): ", 0, sc);

        // Datos propios de cada clase
        switch (categoria) {
            case "cpu": {
                String socket = leerTexto("Socket: ", sc);
                int nucleos = leerEntero("Núcleos: ", 1, sc);
                CPU cpu = new CPU(id, precio, stock, marca, modelo, watts, socket, nucleos);
                guardarCPU(cpu);
                return cpu;
            }
            case "gpu": {
                int vram = leerEntero("VRAM (GB): ", 1, sc);
                GPU gpu = new GPU(id, precio, stock, marca, modelo, watts, vram);
                guardarGPU(gpu);
                return gpu;
            }
            case "ram": {
                String tipo = leerTexto("Tipo (ej. DDR4): ", sc);
                int capacidad = leerEntero("Capacidad (GB): ", 1, sc);
                int mhz = leerEntero("Frecuencia (MHz): ", 1, sc);
                RAM ram = new RAM(id, precio, stock, marca, modelo, watts, tipo, capacidad, mhz);
                guardarRAM(ram);
                return ram;
            }
            case "ssd": {
                int capacidadGB = leerEntero("Capacidad (GB): ", 1, sc);
                String formato = leerTexto("Formato (ej. M.2, SATA): ", sc);
                SSD ssd = new SSD(id, precio, stock, marca, modelo, watts, capacidadGB, formato);
                guardarSSD(ssd);
                return ssd;
            }
            case "gabinete": {
                String tamanio = leerTexto("Tamaño de placa madre soportado (ej. ATX): ", sc);
                Gabinete gabinete = new Gabinete(id, precio, stock, marca, modelo, watts, tamanio);
                guardarGabinete(gabinete);
                return gabinete;
            }
            case "fuente": {
                int poder = leerEntero("Poder de la fuente (W): ", 1, sc);
                String certificado = leerTexto("Certificación (ej. 80 Plus Bronze): ", sc);
                Fuente fuente = new Fuente(id, precio, stock, marca, modelo, watts, poder, certificado);
                guardarFuente(fuente);
                return fuente;
            }
            case "placamadre": {
                String socket = leerTexto("Socket de CPU: ", sc);
                String tipoRam = leerTexto("Tipo de RAM (ej. DDR4): ", sc);
                PlacaMadre placa = new PlacaMadre(id, precio, stock, marca, modelo, watts, socket, tipoRam);
                guardarPlacaMadre(placa);
                return placa;
            }
            default:
                return null;
        }
    }
    
    
    // ---------- Lectura por teclado con validación ----------

    // Pide un ID hasta que no exista ya en la tabla de esa categoría
    private int leerIdNuevo(String categoria, Scanner sc) {
        while (true) {
            int id = leerEntero("ID: ", 0, sc);
            if (obtenerComponenteGeneral(categoria, id) == null) {
                return id;
            }
            System.out.println("Ya existe un componente con ese ID en " + categoria + ", ingrese otro.");
        }
    }

    private int leerEntero(String mensaje, int min, Scanner sc) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor < min) {
                    System.out.println("El valor no puede ser menor a " + min + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje, double min, Scanner sc) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (valor < min) {
                    System.out.println("El valor no puede ser menor a " + min + ".");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private String leerTexto(String mensaje, Scanner sc) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("No puede estar vacío.");
        }
    }
    
    public CPU obtenerCpuPorId(int id) {
        cargarCPUsDesdeBD(); // Refresca la lista desde SQLite
        for (CPU c : listaCPU) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public GPU obtenerGpuPorId(int id) {
        cargarGPUsDesdeBD();
        for (GPU g : listaGPU) {
            if (g.getId() == id) return g;
        }
        return null;
    }

    public PlacaMadre obtenerPlacaMadrePorId(int id) {
        cargarPlacasMadreDesdeBD();
        for (PlacaMadre p : listaPlacaMadre) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public RAM obtenerRamPorId(int id) {
        cargarRAMsDesdeBD();
        for (RAM r : listaRAM) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public Fuente obtenerFuentePorId(int id) {
        cargarFuentesDesdeBD();
        for (Fuente f : listaFuente) {
            if (f.getId() == id) 
                return f;
        }
        return null;
    }

    public SSD obtenerSsdPorId(int id) {
        cargarSSDsDesdeBD();
        for (SSD s : listaSSD) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public Gabinete obtenerGabinetePorId(int id) {
        cargarGabinetesDesdeBD();
        for (Gabinete g : listaGabinete) {
            if (g.getId() == id) return g;
        }
        return null;
    }

 public void cerrarConexion() {
        if (this.conexionDB != null) {
            this.conexionDB.Desconectar();
        }
    }
}