package vista;

import javax.swing.*;
import java.awt.*;

public class vistausuario extends JFrame {
    public JTextField txtId = new JTextField(5);
    public JTextField txtNombre = new JTextField(15);
    public JTextField txtEmail = new JTextField(15);
    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnEditar = new JButton("Editar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JTable tabla = new JTable();
    public DefaultTableModel modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Email"}, 0);

    public vistausuario() {
        setTitle("Gestión de Usuarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(4, 2));
        panelCampos.add(new JLabel("ID:"));
        panelCampos.add(txtId);
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Email:"));
        panelCampos.add(txtEmail);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        tabla.setModel(modeloTabla);

        add(panelCampos, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
}
