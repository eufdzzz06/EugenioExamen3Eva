package controlador;

import modelo.conexionbd;
import modelo.producto;
import vista.vistaproducto;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class productocontrolador {
    private vistaproducto vista;

    public productocontrolador(vistaproducto vista) {
        this.vista = vista;
        cargarDatos();

        vista.btnAgregar.addActionListener(e -> agregarProducto());
        vista.btnEditar.addActionListener(e -> editarProducto());
        vista.btnEliminar.addActionListener(e -> eliminarProducto());

        vista.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                vista.txtId.setText(vista.tabla.getValueAt(fila, 0).toString());
                vista.txtNombre.setText(vista.tabla.getValueAt(fila, 1).toString());
                vista.txtPrecio.setText(vista.tabla.getValueAt(fila, 2).toString());
            }
        });
    }

    private void cargarDatos() {
        try (Connection conn = conexionbd.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");
            vista.modeloTabla.setRowCount(0);
            while (rs.next()) {
                vista.modeloTabla.addRow(new Object[]{
                    rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void agregarProducto() {
        String nombre = vista.txtNombre.getText();
        double precio = Double.parseDouble(vista.txtPrecio.getText());
        try (Connection conn = conexionbd.conectar()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO productos(nombre, precio) VALUES (?, ?)");
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.executeUpdate();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editarProducto() {
        int id = Integer.parseInt(vista.txtId.getText());
        String nombre = vista.txtNombre.getText();
        double precio = Double.parseDouble(vista.txtPrecio.getText());
        try (Connection conn = conexionbd.conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE productos SET nombre=?, precio=? WHERE id=?");
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, id);
            ps.executeUpdate();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminarProducto() {
        int id = Integer.parseInt(vista.txtId.getText());
        try (Connection conn = conexionbd.conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM productos WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

