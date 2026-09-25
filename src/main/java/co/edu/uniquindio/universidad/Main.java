package co.edu.uniquindio.universidad;

import javax.swing.*;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("DevPlus", "900123456", "Calle 1 #2-3", "3001234567", "www.devplus.com");

        int option;
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(
                        "Este es el menú de DevPlus:" +
                                "\n1. Listar clientes" +
                                "\n2. Buscar cliente por teléfono y verificar número perfecto" +
                                "\n3. Mostrar ingresos por fecha de solicitud" +
                                "\n4. Mostrar proyectos" +
                                "\n5. Agregar cliente" +
                                "\n6. Actualizar cliente" +
                                "\n7. Eliminar cliente" +
                                "\n8. Agregar desarrollador" +
                                "\n9. Eliminar desarrollador" +
                                "\n10. Agregar servicio" +
                                "\n11. Eliminar servicio" +
                                "\n12. Crear proyecto (simplificado)" +
                                "\n13. Eliminar proyecto" +
                                "\n0. Salir"
                ));
            } catch (Exception e) {
                option = -1; // opción inválida
            }

            switch(option) {
                case 1:
                    Cliente[] arrC = empresa.getClientesArray();
                    String lista = "";
                    for (int i = 0; i < empresa.getNumClientes(); i++) {
                        lista += (i+1) + ". " + arrC[i] + "\n";
                    }
                    JOptionPane.showMessageDialog(null, lista.isEmpty() ? "No hay clientes" : lista);
                    break;

                case 2:
                    String tel = JOptionPane.showInputDialog("Teléfono a buscar:");
                    Cliente encontrado = empresa.buscarClientePorTelefono(tel);
                    if (encontrado == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    } else {
                        boolean perfecto = empresa.esNumeroPerfecto(Long.parseLong(tel));
                        JOptionPane.showMessageDialog(null, "Cliente: " + encontrado.getNombre() +
                                "\n¿Número perfecto? " + (perfecto ? "Sí" : "No"));
                    }
                    break;

                case 3:
                    String f = JOptionPane.showInputDialog("Fecha (Año-Mes-Dia):");
                    try {
                        LocalDate fecha = LocalDate.parse(f);
                        double ingresos = empresa.ingresosPorFecha(fecha);
                        JOptionPane.showMessageDialog(null, "Ingresos en " + fecha + ": $" + ingresos);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Formato de fecha inválido.");
                    }
                    break;

                case 4:
                    Proyecto[] arrP = empresa.getProyectosArray();
                    String listaP = "";
                    for (int i = 0; i < empresa.getNumProyectos(); i++) {
                        listaP += (i+1) + ". " + arrP[i] + "\n";
                    }
                    JOptionPane.showMessageDialog(null, listaP.isEmpty() ? "No hay proyectos" : listaP);
                    break;

                case 5:
                    String nombre = JOptionPane.showInputDialog("Nombre cliente:");
                    String doc = JOptionPane.showInputDialog("Documento:");
                    String telN = JOptionPane.showInputDialog("Teléfono:");
                    Cliente nuevo = new Cliente(nombre, doc, telN, "", "");
                    boolean agregado = empresa.agregarCliente(nuevo);
                    JOptionPane.showMessageDialog(null, agregado ? "Cliente agregado." : "No se pudo agregar.");
                    break;

                case 6:
                    String docAct = JOptionPane.showInputDialog("Documento cliente a actualizar:");
                    Cliente existente = empresa.buscarClientePorDocumento(docAct);
                    if (existente == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    } else {
                        String nNom = JOptionPane.showInputDialog("Nuevo nombre:");
                        String nTel = JOptionPane.showInputDialog("Nuevo teléfono:");
                        Cliente updated = new Cliente(nNom, docAct, nTel, existente.getCorreo(), existente.getPais());
                        boolean up = empresa.actualizarCliente(docAct, updated);
                        JOptionPane.showMessageDialog(null, up ? "Cliente actualizado." : "No se pudo actualizar.");
                    }
                    break;

                case 7:
                    String docDel = JOptionPane.showInputDialog("Documento cliente a eliminar:");
                    boolean del = empresa.eliminarCliente(docDel);
                    JOptionPane.showMessageDialog(null, del ? "Cliente eliminado." : "No se pudo eliminar.");
                    break;

                case 8:
                    String cod = JOptionPane.showInputDialog("Código desarrollador:");
                    String equipo = JOptionPane.showInputDialog("Equipo:");
                    String nivel = JOptionPane.showInputDialog("Nivel:");
                    int max = Integer.parseInt(JOptionPane.showInputDialog("Max proyectos simultáneos:"));
                    double tarifa = Double.parseDouble(JOptionPane.showInputDialog("Tarifa por día:"));
                    Desarrollador nuevoD = new Desarrollador(cod, equipo, nivel, max, tarifa, "Disponible");
                    boolean addD = empresa.agregarDesarrollador(nuevoD);
                    JOptionPane.showMessageDialog(null, addD ? "Desarrollador agregado." : "No se pudo agregar.");
                    break;

                case 9:
                    String codDel = JOptionPane.showInputDialog("Código desarrollador a eliminar:");
                    boolean delD = empresa.eliminarDesarrollador(codDel);
                    JOptionPane.showMessageDialog(null, delD ? "Desarrollador eliminado." : "No se pudo eliminar.");
                    break;

                case 10:
                    String scod = JOptionPane.showInputDialog("Código servicio:");
                    String snom = JOptionPane.showInputDialog("Nombre servicio:");
                    double sprice = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
                    ServicioAdicional snew = new ServicioAdicional(scod, snom, "", sprice, true);
                    boolean addS = empresa.agregarServicio(snew);
                    JOptionPane.showMessageDialog(null, addS ? "Servicio agregado." : "No se pudo agregar.");
                    break;

                case 11:
                    String scodDel = JOptionPane.showInputDialog("Código servicio a eliminar:");
                    boolean delS = empresa.eliminarServicio(scodDel);
                    JOptionPane.showMessageDialog(null, delS ? "Servicio eliminado." : "No se pudo eliminar.");
                    break;

                case 12:
                    String pcod = JOptionPane.showInputDialog("Código proyecto:");
                    String tcli = JOptionPane.showInputDialog("Teléfono cliente:");
                    Cliente cli = empresa.buscarClientePorTelefono(tcli);
                    if (cli == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        break;
                    }
                    LocalDate fs = LocalDate.parse(JOptionPane.showInputDialog("Fecha solicitud (Año-Mes-Dia):"));
                    LocalDate fi = LocalDate.parse(JOptionPane.showInputDialog("Fecha inicio (Año-Mes-Dia):"));
                    LocalDate fe = LocalDate.parse(JOptionPane.showInputDialog("Fecha entrega (Año-Mes-Dia):"));
                    String est = JOptionPane.showInputDialog("Estado:");
                    String mp = JOptionPane.showInputDialog("Método pago:");
                    double desc = Double.parseDouble(JOptionPane.showInputDialog("Descuento (%):"));
                    Proyecto nuevoP = new Proyecto(pcod, fs, fi, fe, est, mp, cli, desc);

                    String codD = JOptionPane.showInputDialog("Código desarrollador a asignar (ENTER para omitir):");
                    if (!codD.isEmpty()) {
                        Desarrollador dd = empresa.buscarDesarrolladorPorCodigo(codD);
                        if (dd != null) nuevoP.agregarDesarrollador(dd);
                    }

                    String codS = JOptionPane.showInputDialog("Código servicio a agregar (ENTER para omitir):");
                    if (!codS.isEmpty()) {
                        ServicioAdicional ss = empresa.buscarServicioPorCodigo(codS);
                        if (ss != null) nuevoP.agregarServicio(ss);
                    }

                    boolean addP = empresa.agregarProyecto(nuevoP);
                    JOptionPane.showMessageDialog(null, addP ? "Proyecto creado." : "No se pudo crear.");
                    break;

                case 13:
                    String pdel = JOptionPane.showInputDialog("Código proyecto a eliminar:");
                    boolean delP = empresa.eliminarProyecto(pdel);
                    JOptionPane.showMessageDialog(null, delP ? "Proyecto eliminado." : "No se pudo eliminar.");
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while(option != 0);
    }


}