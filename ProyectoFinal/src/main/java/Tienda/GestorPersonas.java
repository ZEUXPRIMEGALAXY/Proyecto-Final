/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tienda;

import com.mycompany.proyectofinal.Conexion.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maximo
 */
public class GestorPersonas {
    private ConexionDB con;
    public GestorPersonas() {
        this.con = new ConexionDB();
        this.con.Conectar();
        crearTablasPersonas();
    }

private void crearTablasPersonas() {
        String sqlCliente = "CREATE TABLE IF NOT EXISTS cliente (id INTEGER PRIMARY KEY, nombre TEXT)";
        String sqlEmpleado = "CREATE TABLE IF NOT EXISTS empleado (id INTEGER PRIMARY KEY, nombre TEXT, telefono TEXT)";
        
        try (Statement stmt = con.getCon().createStatement()) {
            stmt.execute(sqlCliente);
            stmt.execute(sqlEmpleado);
        } catch (SQLException e) {
            System.out.println("Error al crear tablas en tienda.db: " + e.getMessage());
        }
    }

    public void guardarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente(id, nombre) VALUES(?,?)";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Error al guardar Cliente: " + e.getMessage()); }
    }

    public void guardarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado(id, nombre, telefono) VALUES(?,?,?)";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, empleado.getId());
            pstmt.setString(2, empleado.getNombre());
            pstmt.setString(3, empleado.getTelefono());
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Error al guardar Empleado: " + e.getMessage()); }
    }
    
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            // Si el cliente existe, instanciamos el objeto con los datos de SQLite
            if (rs.next()) {
                return new Cliente(rs.getString("nombre"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar Cliente en la BD: " + e.getMessage());
        }
        
        return null; // Retorna null si el ID no existe
    }
    
    public Empleado obtenerEmpleadoPorId(int id) {
        String sql = "SELECT * FROM empleado WHERE id = ?";
        
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            // Si el empleado existe, instanciamos el objeto con los datos de SQLite
            if (rs.next()) {
                return new Empleado(rs.getString("nombre"), rs.getInt("id"), rs.getString("telefono"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar Empleado en la BD: " + e.getMessage());
        }
        
        return null; // Retorna null si el ID no existe
    }
}
