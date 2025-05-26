import javax.swing.*;

import vista.vistaproducto;
import vista.vistausuario;

public class mainapp {
    public static void main(String[] args) {
        String[] opciones = {"Productos", "Usuarios"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una vista",
            "Gestión", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == 0) {
            vistaproducto vistaProducto = new vistaproducto();
            new productocontrolador(vistaProducto);
        } else if (seleccion == 1) {
            vistausuario vistaUsuario = new vistausuario();
            new usuariocontrolador(vistaUsuario);
        }
    }
}
