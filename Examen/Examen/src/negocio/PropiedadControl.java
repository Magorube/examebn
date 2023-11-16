/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.PropiedadDAO;
import dominio.Propiedad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALUMNO
 */
public class PropiedadControl {
    private final PropiedadDAO DATOS;
    private Propiedad obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public PropiedadControl() {
        this.DATOS = new PropiedadDAO();
        this.obj = new Propiedad();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Propiedad> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"Id", "Nombre", "Dirección", "Estado", "Precio Alquiler", "Fecha Creación", "Fecha Actualización"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[7];

        this.registrosMostrados = 0;
        for (Propiedad item : lista) {
            if (item.getEstado().equals("Activo")) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDireccion();
            registro[3] = estado;
            registro[4] = Float.toString(item.getPrecioAlquiler());
            registro[5] = item.getCreatedAt().toString();  // Aquí puedes ajustar según tu necesidad
            registro[6] = item.getUpdatedAt().toString();  // Aquí puedes ajustar según tu necesidad
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }

    // Puedes adaptar los métodos de insertar, actualizar, desactivar, activar según tu lógica y necesidades

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}