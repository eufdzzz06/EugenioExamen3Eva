package controlador;

import modelo.conexionbd;
import modelo.usuario;
import vista.vistausuario;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class usuariocontrolador {
    private vistausuario vista;

    public usuariocontrolador(vistausuario vista) {
        this.vista = vista;
        cargarDatos();

        vista.btnAgregar.addActionListener(e -> agregarUsuario());
        vista.btnEditar.addActionListener(e -> editarUsuario());
        vista.btnEliminar.addActionListener(e -> eliminarUsuario());

        vista.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                vista.txtId.setText(vista.tabla.getValueAt(fila, 0).toString());
                vista.txtNombre.setText(vista.tabla.getValueAt(fila, 1).toString());
                vista.txtEmail.setText(vista.tabla.getValueAt(fila, 2).toString());
            }
        });
    }

    private void cargarDatos() {
        try (Connection conn = conexionbd.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            vista.modeloTabla.setRowCount(0); // Limpiar tabla
            while (rs.next()) {
                vista.modeloTabla.addRow(new Object[]{
                    rs.getInt("id"), rs.getString("nombre"), rs.getString("email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void agregarUsuario() {
        String nombre = vista.txtNombre.getText();
        String email = vista.txtEmail.getText();
        try (Connection conn = conexionbd.conectar()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios(nombre, email) VALUES (?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.executeUpdate();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editarUsuario() {
        int id = Integer.parseInt(vista.txtId.getText());
        String nombre = vista.txtNombre.getText();
        String email = vista.txtEmail.getText();
        try (Connection conn = conexionbd.conectar()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET nombre=?, email=? WHERE id=?");
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.executeUpdate();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void eliminarUsuario() {
        int id = Integer.parseInt(vista.txtId.getText());
        try (Connection conn = conexionbd.conectar()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
